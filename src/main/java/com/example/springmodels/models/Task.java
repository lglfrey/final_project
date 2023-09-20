package com.example.springmodels.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity implements Serializable, GenericEntity<Task> {

    @NotBlank(message = "Название задачи не может быть пустым")
    @Size(min = 5, max = 100, message = "Название задачи должно содержать от 5 до 100 символов")
    @Column(name = "task_title")
    private String taskTitle;

    @Size(max = 500, message = "Описание задачи не должно превышать 500 символов")
    @Column(name = "task_description")
    private String taskDescription;

    @NotBlank(message = "Статус задачи не может быть пустым")
    @Column(name = "task_status")
    private String taskStatus;

    @NotBlank(message = "Приоритет задачи не может быть пустым")
    @Column(name = "task_priority")
    private String taskPriority;

    @NotNull(message = "Дедлайн задачи не может быть пустым")
    @FutureOrPresent(message = "Дедлайн задачи должен быть будущим или текущим")
    @Column(name = "deadline")
    private String deadline;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    public Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private modelUser modelUser;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TaskCategory taskCategory;

    @PrePersist // Вызывается перед сохранением новой записи
    public void prePersist() {
        this.createdAt = new Date(); // Устанавливаем текущую дату и время при создании
        this.updatedAt = new Date(); // Устанавливаем текущую дату и время при создании
    }

    @PreUpdate // Вызывается перед обновлением записи
    public void preUpdate() {
        this.updatedAt = new Date(); // Устанавливаем текущую дату и время при обновлении
    }

    @Override
    public long getId() {
        return this.id;
    }
    @Override
    public void update(Task source) {
        this.taskTitle = source.taskTitle;
        this.taskDescription = source.taskDescription;
        this.taskStatus = source.taskStatus;
        this.taskPriority = source.taskPriority;
        this.deadline = source.deadline;
        this.modelUser = source.modelUser;
        this.taskCategory = source.taskCategory;
        this.createdAt = source.createdAt;
        this.updatedAt = source.updatedAt;
    }

    @Override
    public Task createNewInstance() {
        Task task = new Task();
        task.update(this);
        return task;
    }
}

