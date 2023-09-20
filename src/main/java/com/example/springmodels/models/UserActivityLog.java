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
@Table(name = "user_activity_log")
public class UserActivityLog extends AbstractEntity implements Serializable, GenericEntity<UserActivityLog> {

    @NotBlank(message = "Текст действия не может быть пустым")
    @Size(max = 500, message = "Текст действия не должен превышать 500 символов")
    @Column(name = "action_text")
    private String actionText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    public Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @PrePersist // Вызывается перед сохранением новой записи
    public void prePersist() {
        this.createdAt = new Date(); // Устанавливаем текущую дату и время при создании
        this.updatedAt = new Date(); // Устанавливаем текущую дату и время при создании
    }

    @PreUpdate // Вызывается перед обновлением записи
    public void preUpdate() {
        this.updatedAt = new Date(); // Устанавливаем текущую дату и время при обновлении
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    private modelUser modelUser;

    @Override
    public void update(UserActivityLog source) {
        this.actionText = source.actionText;
        this.createdAt = source.createdAt;
        this.updatedAt = source.updatedAt;
        this.modelUser = source.modelUser;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public UserActivityLog createNewInstance() {
        UserActivityLog userActivityLog = new UserActivityLog();
        userActivityLog.update(this);
        return userActivityLog;
    }
}
