package com.example.springmodels.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "task_comments")
public class TaskComment extends AbstractEntity implements Serializable, GenericEntity<TaskComment> {

    @NotBlank(message = "Текст комментария не может быть пустым")
    @Size(max = 500, message = "Текст комментария не должен превышать 500 символов")
    @Column(name = "comment_text")
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private modelUser modelUser;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Override
    public void update(TaskComment source) {
        this.commentText = source.commentText;
        this.modelUser = source.modelUser;
        this.task = source.task;

    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public TaskComment createNewInstance() {
        TaskComment taskComment = new TaskComment();
        taskComment.update(this);
        return taskComment;
    }
}
