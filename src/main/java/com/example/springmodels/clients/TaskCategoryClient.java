package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.TaskCategory;
import com.example.springmodels.summary.TaskCategorySummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TaskCategoryClient extends GenericRestClient<TaskCategory, TaskCategorySummary> {
    public TaskCategoryClient() {
        super(ApiHelper.getTask_CategoryUrl());
    }
    @Override
    public List<TaskCategory> findAll(String url) {
        ResponseEntity<TaskCategorySummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskCategorySummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public TaskCategory findById(String url, long id) {
        ResponseEntity<TaskCategory> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskCategory>() {});
        return response.getBody();
    }

    @Override
    public TaskCategory create(String url, TaskCategory entity){
        ResponseEntity<TaskCategory> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskCategory>() {});

        return response.getBody();
    }

    @Override
    public TaskCategory update(String url, TaskCategory entity){
        ResponseEntity<TaskCategory> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskCategory>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<TaskCategory> findAll() {
        ResponseEntity<TaskCategorySummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskCategorySummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public TaskCategory findById(long id) {
        ResponseEntity<TaskCategory> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskCategory>() {});
        return response.getBody();
    }

    @Override
    public TaskCategory create(TaskCategory entity){
        ResponseEntity<TaskCategory> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskCategory>() {});

        return response.getBody();
    }

    @Override
    public TaskCategory update(TaskCategory entity){
        ResponseEntity<TaskCategory> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskCategory>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
