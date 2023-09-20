package com.example.springmodels.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
@Data
@Entity
@Table(name = "task_categories")
public class TaskCategory extends AbstractEntity implements Serializable, GenericEntity<TaskCategory> {

    @NotBlank(message = "Название категории задач не может быть пустым")
    @Size(min = 3, max = 50, message = "Название категории задач должно содержать от 3 до 50 символов")
    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "taskCategory")
    private Set<Task> tasks;

    @Override
    public void update(TaskCategory source) {
        this.categoryName = source.categoryName;
        this.tasks = source.tasks;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public TaskCategory createNewInstance() {
        TaskCategory taskCategory = new TaskCategory();
        taskCategory.update(this);
        return taskCategory;
    }
}
