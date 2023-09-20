package com.example.springmodels.controllers;

import com.example.springmodels.models.*;
import com.example.springmodels.clients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/allcrud")
@PreAuthorize("hasAnyAuthority('3', '1')")
public class AllCRUDController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    //region Clients
    private final ProjectClient projectClient = new ProjectClient();
    private final TaskAttachmentClient taskAttachmentClient = new TaskAttachmentClient();
    private final TaskCategoryClient taskCategoryClient = new TaskCategoryClient();
    private final TaskClient taskClient = new TaskClient();
    private final TaskCommentClient taskCommentClient = new TaskCommentClient();
    private final UserActivityLogClient userActivityLogClient = new UserActivityLogClient();
    private final UserProjectClient userProjectClient = new UserProjectClient();
    private final UserClient userClient = new UserClient();
    private final RoleClient roleClient = new RoleClient();
    //endregion

    //region Users
    @GetMapping("/users")
    public String indexUsers(Model model) {
        List<modelUser> users = userClient.findAll();
        users.removeIf(u -> u.getUsername().equals(MainController.getUsername()));

        model.addAttribute("users", users);
        return "allcrud/users/index";
    }

    @GetMapping("/users/search")
    public String searchUsers(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        modelUser user = userClient.findById(id);
        if (user.getUsername().equals(MainController.getUsername()))
            throw new IllegalArgumentException("Invalid user Id:" + id);

        model.addAttribute("user", user);
        return "allcrud/users/show";
    }

    @GetMapping("/users/{id}")
    public String showUsers(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userClient.findById(id));
        return "allcrud/users/show";
    }

    @GetMapping("/users/new")
    public String newEntityUsers(@ModelAttribute("user") modelUser entity, Model model) {
        model.addAttribute("roles", roleClient.findAll());

        return "allcrud/users/new";
    }

    @PostMapping("/users")
    public String createUsers(@RequestParam("role") long role,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password) {
        modelUser user = new modelUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleClient.findById(role));
        user.setActive(true);

        userClient.create(user);
        return "redirect:/allcrud/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUsers(Model model, @PathVariable("id") long id) {
        model.addAttribute("roles", roleClient.findAll());

        model.addAttribute("user", userClient.findById(id));
        return "allcrud/users/edit";
    }

    @PostMapping("/users/{id}")
    public String updateUsers(@PathVariable("id") long id,
                              @RequestParam("role") long role,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password) {
        modelUser user = userClient.findById(id);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleClient.findById(role));

        userClient.update(user);
        return "redirect:/allcrud/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUsers(@PathVariable("id") long id) {
        userClient.deleteById(id);
        return "redirect:/allcrud/users";
    }
    //endregion

    //region Project
    @GetMapping("/projects")
    public String indexProjects(Model model) {
        List<Project> projects = projectClient.findAll();
        model.addAttribute("projects", projects);
        return "allcrud/projects/index";
    }

    @GetMapping("/projects/search")
    public String searchProject(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Project project = projectClient.findById(id);
        model.addAttribute("project", project);
        return "allcrud/projects/show";
    }

    @GetMapping("/projects/{id}")
    public String showProject(@PathVariable("id") long id, Model model) {
        Project project = projectClient.findById(id);
        model.addAttribute("project", project);
        return "allcrud/projects/show";
    }

    @GetMapping("/projects/new")
    public String newProjectForm(Model model) {
        List<modelUser> employees = userClient.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("project", new Project());
        return "allcrud/projects/new";
    }

    @PostMapping("/projects")
    public String createProject(@ModelAttribute Project project) {
        project.setCreatedAt(new Date());
        projectClient.create(project);
        return "redirect:/allcrud/projects";
    }

    @GetMapping("/projects/{id}/edit")
    public String editProjectForm(@PathVariable("id") long id, Model model) {
        List<modelUser> employees = userClient.findAll();
        model.addAttribute("employees", employees);
        Project project = projectClient.findById(id);
        model.addAttribute("project", project);
        return "allcrud/projects/edit";
    }

    @PostMapping("/projects/{id}")
    public String updateProject(@PathVariable("id") long id, @ModelAttribute Project project) {
        Project existingProject = projectClient.findById(id);
        existingProject.setProjectTitle(project.getProjectTitle());
        existingProject.setProjectDescription(project.getProjectDescription());
        existingProject.setModelUser(project.getModelUser());
        projectClient.update(existingProject);
        return "redirect:/allcrud/projects";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable("id") long id) {
        projectClient.deleteById(id);
        return "redirect:/allcrud/projects";
    }
    //endregion

    //region Roles
    @GetMapping("/roles")
    public String indexRoles(Model model) {
        List<Role> roles = roleClient.findAll();

        model.addAttribute("roles", roles);
        return "allcrud/roles/index";
    }

    @GetMapping("/roles/search")
    public String searchRoles(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        if (id < 1 && id > 3)
            throw new IllegalArgumentException("Invalid role Id:" + id);

        Role role = roleClient.findById(id);

        model.addAttribute("role", role);
        return "allcrud/roles/show";
    }

    @GetMapping("/roles/{id}")
    public String showRoles(@PathVariable("id") long id, Model model) {
        model.addAttribute("role", roleClient.findById(id));
        return "allcrud/roles/show";
    }

    @GetMapping("/roles/new")
    public String newEntityRoles(@ModelAttribute("role") Role entity, Model model) {
        return "allcrud/roles/new";
    }

    @PostMapping("/roles")
    public String createRoles(@RequestParam("name") String name) {
        Role existing = roleClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("Role with this name already exists");

        Role role = new Role();
        role.setName(name);

        roleClient.create(role);
        return "redirect:/allcrud/roles";
    }

    @GetMapping("/roles/{id}/edit")
    public String editRoles(Model model, @PathVariable("id") long id) {
        model.addAttribute("role", roleClient.findById(id));
        return "allcrud/roles/edit";
    }

    @PostMapping("/roles/{id}")
    public String updateRoles(@PathVariable("id") long id,
                              @RequestParam("name") String name) {
        Role existing = roleClient.findAll().stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
        if (existing != null)
            throw new IllegalArgumentException("Role with this name already exists");

        Role role = roleClient.findById(id);
        role.setName(name);

        roleClient.update(role);
        return "redirect:/allcrud/roles";
    }

    @GetMapping("/roles/delete/{id}")
    public String deleteRoles(@PathVariable("id") long id) {
        if (id >= 1 && id <= 4)
            throw new IllegalArgumentException("Invalid role Id:" + id);

        roleClient.deleteById(id);
        return "redirect:/allcrud/roles";
    }
    //endregion

    @GetMapping("/tasks")
    public String indexTasks(Model model) {
        List<Task> tasks = taskClient.findAll();
        model.addAttribute("tasks", tasks);
        return "allcrud/tasks/index";
    }

    @GetMapping("/tasks/search")
    public String searchTask(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        Task task = taskClient.findById(id);
        model.addAttribute("task", task);
        return "allcrud/tasks/show";
    }

    @GetMapping("/tasks/{id}")
    public String showTask(@PathVariable("id") long id, Model model) {
        Task task = taskClient.findById(id);
        model.addAttribute("task", task);
        return "allcrud/tasks/show";
    }

    @GetMapping("/tasks/new")
    public String newTaskForm(Model model) {
        List<modelUser> users = userClient.findAll();
        List<TaskCategory> tasks = taskCategoryClient.findAll();
        model.addAttribute("users", users);
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskcat", new Task());
        return "allcrud/tasks/new";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task) {
        task.setCreatedAt(new Date());
        taskClient.create(task);
        return "redirect:/allcrud/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String editTaskForm(@PathVariable("id") long id, Model model) {
        List<modelUser> users = userClient.findAll();
        model.addAttribute("users", users);
        Task task = taskClient.findById(id);
        model.addAttribute("task", task);
        return "allcrud/tasks/edit";
    }

    @PostMapping("/tasks/{id}")
    public String updateTask(@PathVariable("id") long id, @ModelAttribute Task task) {
        Task existingTask = taskClient.findById(id);
        existingTask.setTaskTitle(task.getTaskTitle());
        existingTask.setTaskDescription(task.getTaskDescription());
        existingTask.setTaskStatus(task.getTaskStatus());
        existingTask.setTaskPriority(task.getTaskPriority());
        existingTask.setDeadline(task.getDeadline());
        existingTask.setModelUser(task.getModelUser());
        taskClient.update(existingTask);
        return "redirect:/allcrud/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        taskClient.deleteById(id);
        return "redirect:/allcrud/tasks";
    }
    //
    @GetMapping("/task_attachments")
    public String indexTaskAttachments(Model model) {
        List<TaskAttachment> taskAttachments = taskAttachmentClient.findAll();
        model.addAttribute("taskAttachments", taskAttachments);
        return "allcrud/task_attachments/index";
    }

    @GetMapping("/task_attachments/search")
    public String searchTaskAttachment(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        TaskAttachment taskAttachment = taskAttachmentClient.findById(id);
        model.addAttribute("taskAttachment", taskAttachment);
        return "allcrud/task_attachments/show";
    }

    @GetMapping("/task_attachments/{id}")
    public String showTaskAttachment(@PathVariable("id") long id, Model model) {
        TaskAttachment taskAttachment = taskAttachmentClient.findById(id);
        model.addAttribute("taskAttachment", taskAttachment);
        return "allcrud/task_attachments/show";
    }

    @GetMapping("/task_attachments/new")
    public String newTaskAttachmentForm(Model model) {
        List<Task> tasks = taskClient.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskAttachment", new TaskAttachment());
        return "allcrud/task_attachments/new";
    }

    @PostMapping("/task_attachments")
    public String createTaskAttachment(@ModelAttribute TaskAttachment taskAttachment) {
        taskAttachmentClient.create(taskAttachment);
        return "redirect:/allcrud/task_attachments";
    }

    @GetMapping("/task_attachments/{id}/edit")
    public String editTaskAttachmentForm(@PathVariable("id") long id, Model model) {
        List<Task> tasks = taskClient.findAll();
        model.addAttribute("tasks", tasks);
        TaskAttachment taskAttachment = taskAttachmentClient.findById(id);
        model.addAttribute("taskAttachment", taskAttachment);
        return "allcrud/task_attachments/edit";
    }

    @PostMapping("/task_attachments/{id}")
    public String updateTaskAttachment(@PathVariable("id") long id, @ModelAttribute TaskAttachment taskAttachment) {
        TaskAttachment existingTaskAttachment = taskAttachmentClient.findById(id);
        existingTaskAttachment.setFileName(taskAttachment.getFileName());
        existingTaskAttachment.setFileURL(taskAttachment.getFileURL());
        existingTaskAttachment.setTask(taskAttachment.getTask());
        taskAttachmentClient.update(existingTaskAttachment);
        return "redirect:/allcrud/task_attachments";
    }

    @GetMapping("/task_attachments/delete/{id}")
    public String deleteTaskAttachment(@PathVariable("id") long id) {
        taskAttachmentClient.deleteById(id);
        return "redirect:/allcrud/task_attachments";
    }
    //
    @GetMapping("/task_categories")
    public String indexTaskCategories(Model model) {
        List<TaskCategory> taskCategories = taskCategoryClient.findAll();
        model.addAttribute("taskCategories", taskCategories);
        return "allcrud/task_categories/index";
    }

    @GetMapping("/task_categories/search")
    public String searchTaskCategory(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        TaskCategory taskCategory = taskCategoryClient.findById(id);
        model.addAttribute("taskCategory", taskCategory);
        return "allcrud/ask_categories/show";
    }

    @GetMapping("/task_categories/{id}")
    public String showTaskCategory(@PathVariable("id") long id, Model model) {
        TaskCategory taskCategory = taskCategoryClient.findById(id);
        model.addAttribute("taskCategory", taskCategory);
        return "allcrud/task_categories/show";
    }

    @GetMapping("/task_categories/new")
    public String newTaskCategoryForm(Model model) {
        model.addAttribute("taskCategory", new TaskCategory());
        return "allcrud/task_categories/new";
    }

    @PostMapping("/task_categories")
    public String createTaskCategory(@ModelAttribute TaskCategory taskCategory) {
        taskCategoryClient.create(taskCategory);
        return "redirect:/allcrud/task_categories";
    }

    @GetMapping("/task_categories/{id}/edit")
    public String editTaskCategoryForm(@PathVariable("id") long id, Model model) {
        TaskCategory taskCategory = taskCategoryClient.findById(id);
        model.addAttribute("taskCategory", taskCategory);
        return "allcrud/task_categories/edit";
    }

    @PostMapping("/task_categories/{id}")
    public String updateTaskCategory(@PathVariable("id") long id, @ModelAttribute TaskCategory taskCategory) {
        TaskCategory existingTaskCategory = taskCategoryClient.findById(id);
        existingTaskCategory.setCategoryName(taskCategory.getCategoryName());
        taskCategoryClient.update(existingTaskCategory);
        return "redirect:/allcrud/task_categories";
    }

    @GetMapping("/task_categories/delete/{id}")
    public String deleteTaskCategory(@PathVariable("id") long id) {
        taskCategoryClient.deleteById(id);
        return "redirect:/allcrud/task_categories";
    }
    //
    @GetMapping("/task_comments")
    public String indexTaskComments(Model model) {
        List<TaskComment> taskComments = taskCommentClient.findAll();
        model.addAttribute("taskComments", taskComments);
        return "allcrud/task_comments/index";
    }

    @GetMapping("/task_comments/search")
    public String searchTaskComment(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        TaskComment taskComment = taskCommentClient.findById(id);
        model.addAttribute("taskComment", taskComment);
        return "allcrud/task_comments/show";
    }

    @GetMapping("/task_comments/{id}")
    public String showTaskComment(@PathVariable("id") long id, Model model) {
        TaskComment taskComment = taskCommentClient.findById(id);
        model.addAttribute("taskComment", taskComment);
        return "allcrud/task_comments/show";
    }

    @GetMapping("/task_comments/new")
    public String newTaskCommentForm(Model model) {
        model.addAttribute("taskComment", new TaskComment());
        return "allcrud/task_comments/new";
    }

    @PostMapping("/task_comments")
    public String createTaskComment(@ModelAttribute TaskComment taskComment) {
        taskCommentClient.create(taskComment);
        return "redirect:/allcrud/task_comments";
    }

    @GetMapping("/task_comments/{id}/edit")
    public String editTaskCommentForm(@PathVariable("id") long id, Model model) {
        TaskComment taskComment = taskCommentClient.findById(id);
        model.addAttribute("taskComment", taskComment);
        return "allcrud/task_comments/edit";
    }

    @PostMapping("/task_comments/{id}")
    public String updateTaskComment(@PathVariable("id") long id, @ModelAttribute TaskComment taskComment) {
        TaskComment existingTaskComment = taskCommentClient.findById(id);
        existingTaskComment.setCommentText(taskComment.getCommentText());
        taskCommentClient.update(existingTaskComment);
        return "redirect:/allcrud/task_comments";
    }

    @GetMapping("/task_comments/delete/{id}")
    public String deleteTaskComment(@PathVariable("id") long id) {
        taskCommentClient.deleteById(id);
        return "redirect:/allcrud/task_comments";
    }
    //
    @GetMapping("/user_activity_logs")
    public String indexUserActivityLogs(Model model) {
        List<UserActivityLog> userActivityLogs = userActivityLogClient.findAll();
        model.addAttribute("userActivityLogs", userActivityLogs);
        return "allcrud/user_activity_logs/index";
    }

    @GetMapping("/user_activity_logs/search")
    public String searchUserActivityLog(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        UserActivityLog userActivityLog = userActivityLogClient.findById(id);
        model.addAttribute("userActivityLog", userActivityLog);
        return "allcrud/user_activity_logs/show";
    }

    @GetMapping("/user_activity_logs/{id}")
    public String showUserActivityLog(@PathVariable("id") long id, Model model) {
        UserActivityLog userActivityLog = userActivityLogClient.findById(id);
        model.addAttribute("userActivityLog", userActivityLog);
        return "allcrud/user_activity_logs/show";
    }

    @GetMapping("/user_activity_logs/new")
    public String newUserActivityLogForm(Model model) {
        model.addAttribute("userActivityLog", new UserActivityLog());
        return "allcrud/user_activity_logs/new";
    }

    @PostMapping("/user_activity_logs")
    public String createUserActivityLog(@ModelAttribute UserActivityLog userActivityLog) {
        userActivityLogClient.create(userActivityLog);
        return "redirect:/allcrud/user_activity_logs";
    }

    @GetMapping("/user_activity_logs/{id}/edit")
    public String editUserActivityLogForm(@PathVariable("id") long id, Model model) {
        UserActivityLog userActivityLog = userActivityLogClient.findById(id);
        model.addAttribute("userActivityLog", userActivityLog);
        return "allcrud/user_activity_logs/edit";
    }

    @PostMapping("/user_activity_logs/{id}")
    public String updateUserActivityLog(@PathVariable("id") long id, @ModelAttribute UserActivityLog userActivityLog) {
        UserActivityLog existingUserActivityLog = userActivityLogClient.findById(id);
        existingUserActivityLog.setActionText(userActivityLog.getActionText());
        userActivityLogClient.update(existingUserActivityLog);
        return "redirect:/allcrud/user_activity_logs";
    }

    @GetMapping("/user_activity_logs/delete/{id}")
    public String deleteUserActivityLog(@PathVariable("id") long id) {
        userActivityLogClient.deleteById(id);
        return "redirect:/allcrud/user_activity_logs";
    }
    //
    @GetMapping("/user_projects")
    public String indexUserProjects(Model model) {
        List<UserProject> userProjects = userProjectClient.findAll();
        model.addAttribute("userProjects", userProjects);
        return "allcrud/user_projects/index";
    }

    @GetMapping("/user_projects/search")
    public String searchUserProject(@RequestParam(value = "id", defaultValue = "-1") long id, Model model) {
        UserProject userProject = userProjectClient.findById(id);
        model.addAttribute("userProject", userProject);
        return "allcrud/user_projects/show";
    }

    @GetMapping("/user_projects/{id}")
    public String showUserProject(@PathVariable("id") long id, Model model) {
        UserProject userProject = userProjectClient.findById(id);
        model.addAttribute("userProject", userProject);
        return "allcrud/user_projects/show";
    }

    @GetMapping("/user_projects/new")
    public String newUserProjectForm(Model model) {
        model.addAttribute("userProject", new UserProject());
        return "allcrud/user_projects/new";
    }

    @PostMapping("/user_projects")
    public String createUserProject(@ModelAttribute UserProject userProject) {
        userProjectClient.create(userProject);
        return "redirect:/allcrud/user_projects";
    }

    @GetMapping("/user_projects/{id}/edit")
    public String editUserProjectForm(@PathVariable("id") long id, Model model) {
        UserProject userProject = userProjectClient.findById(id);
        model.addAttribute("userProject", userProject);
        return "allcrud/user_projects/edit";
    }

    @PostMapping("/user_projects/{id}")
    public String updateUserProject(@PathVariable("id") long id, @ModelAttribute UserProject userProject) {
        UserProject existingUserProject = userProjectClient.findById(id);
        existingUserProject.setUser(userProject.getUser());
        existingUserProject.setProject(userProject.getProject());
        userProjectClient.update(existingUserProject);
        return "redirect:/allcrud/user_projects";
    }

    @GetMapping("/user_projects/delete/{id}")
    public String deleteUserProject(@PathVariable("id") long id) {
        userProjectClient.deleteById(id);
        return "redirect:/allcrud/user_projects";
    }
}
