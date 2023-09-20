package com.example.springmodels.models;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "task_attachments")
public class TaskAttachment extends AbstractEntity implements Serializable, GenericEntity<TaskAttachment> {

    @NotBlank(message = "Имя файла не может быть пустым")
    @Size(max = 255, message = "Имя файла не должно превышать 255 символов")
    @Column(name = "file_name")
    private String fileName;

    @URL(message = "URL файла должен быть корректным")
    @Column(name = "file_url")
    private String fileURL;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Override
    public void update(TaskAttachment source) {
        this.fileName = source.fileName;
        this.fileURL = source.fileURL;
        this.task = source.task;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public TaskAttachment createNewInstance() {
        TaskAttachment taskAttachment = new TaskAttachment();
        taskAttachment.update(this);
        return taskAttachment;
    }
}
