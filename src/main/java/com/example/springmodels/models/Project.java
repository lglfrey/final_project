package com.example.springmodels.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "projects")
public class Project extends AbstractEntity implements Serializable, GenericEntity<Project> {
    @NotBlank(message = "Название проекта не может быть пустым")
    @Size(min = 5, max = 100, message = "Название проекта должно содержать от 5 до 100 символов")
    @Column(name = "project_title")
    private String projectTitle;

    @Size(max = 500, message = "Описание проекта не должно превышать 500 символов")
    @Column(name = "project_description")
    private String projectDescription;

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
    public void update(Project source) {
        this.projectTitle = source.projectTitle;
        this.projectDescription = source.projectDescription;
        this.createdAt = source.createdAt;
        this.updatedAt = source.updatedAt;
        this.modelUser = source.modelUser;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public Project createNewInstance() {
        Project project = new Project();
        project.update(this);
        return  project;
    }
}
