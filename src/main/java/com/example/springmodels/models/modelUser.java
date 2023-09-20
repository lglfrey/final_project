package com.example.springmodels.models;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
public class modelUser extends AbstractEntity implements Serializable, GenericEntity<modelUser> {

    @NotNull(message = "Имя пользователя не может быть пустым")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    private String username;
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 1, message = "Пароль должен содержать минимум 1 символ")
    private String password;
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;
    @Override
    public void update(modelUser source) {
        this.username = source.username;
        this.password = source.password;
        this.active = source.active;
        this.role = source.role;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public modelUser createNewInstance() {
        modelUser newInstance = new modelUser();
        newInstance.update(this);
        return newInstance;
    }
}
