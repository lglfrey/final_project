package com.example.springmodels.controllers;

import com.example.springmodels.clients.ProjectClient;
import com.example.springmodels.clients.UserClient;
import com.example.springmodels.models.Project;
import com.example.springmodels.models.modelUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/views")
@PreAuthorize("hasAnyAuthority('1')")
public class forUsersController {
    private final ProjectClient projectClient = new ProjectClient();
    @GetMapping()
    public String index(Model model) {
        List<Project> projects = projectClient.findAll();

        model.addAttribute("test", projects);
        return "/indexUser";
    }

}
