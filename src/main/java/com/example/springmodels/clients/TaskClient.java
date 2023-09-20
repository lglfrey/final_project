package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.Task;
import com.example.springmodels.summary.TaskSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TaskClient extends GenericRestClient<Task, TaskSummary> {
    public TaskClient() {
        super(ApiHelper.getTaskUrl());
    }
    @Override
    public List<Task> findAll(String url) {
        ResponseEntity<TaskSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Task findById(String url, long id) {
        ResponseEntity<Task> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Task>() {});
        return response.getBody();
    }

    @Override
    public Task create(String url, Task entity){
        ResponseEntity<Task> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Task>() {});

        return response.getBody();
    }

    @Override
    public Task update(String url, Task entity){
        ResponseEntity<Task> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Task>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<Task> findAll() {
        ResponseEntity<TaskSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Task findById(long id) {
        ResponseEntity<Task> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Task>() {});
        return response.getBody();
    }

    @Override
    public Task create(Task entity){
        ResponseEntity<Task> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Task>() {});

        return response.getBody();
    }

    @Override
    public Task update(Task entity){
        ResponseEntity<Task> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Task>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
