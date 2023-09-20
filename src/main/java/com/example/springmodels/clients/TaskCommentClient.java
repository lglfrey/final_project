package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.TaskComment;
import com.example.springmodels.models.TaskComment;
import com.example.springmodels.summary.TaskCommentSummary;
import com.example.springmodels.summary.TaskCommentSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TaskCommentClient extends GenericRestClient<TaskComment, TaskCommentSummary> {
    public TaskCommentClient() {
        super(ApiHelper.getTask_CommentUrl());
    }
    @Override
    public List<TaskComment> findAll(String url) {
        ResponseEntity<TaskCommentSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskCommentSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public TaskComment findById(String url, long id) {
        ResponseEntity<TaskComment> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskComment>() {});
        return response.getBody();
    }

    @Override
    public TaskComment create(String url, TaskComment entity){
        ResponseEntity<TaskComment> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskComment>() {});

        return response.getBody();
    }

    @Override
    public TaskComment update(String url, TaskComment entity){
        ResponseEntity<TaskComment> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskComment>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<TaskComment> findAll() {
        ResponseEntity<TaskCommentSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskCommentSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public TaskComment findById(long id) {
        ResponseEntity<TaskComment> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskComment>() {});
        return response.getBody();
    }

    @Override
    public TaskComment create(TaskComment entity){
        ResponseEntity<TaskComment> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskComment>() {});

        return response.getBody();
    }

    @Override
    public TaskComment update(TaskComment entity){
        ResponseEntity<TaskComment> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskComment>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
