package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.TaskAttachment;
import com.example.springmodels.models.TaskAttachment;
import com.example.springmodels.summary.TaskAttachmentSummary;
import com.example.springmodels.summary.TaskAttachmentSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TaskAttachmentClient extends GenericRestClient<TaskAttachment, TaskAttachmentSummary> {
    public TaskAttachmentClient() {
        super(ApiHelper.getTask_AttachmentUrl());
    }
    @Override
    public List<TaskAttachment> findAll(String url) {
        ResponseEntity<TaskAttachmentSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskAttachmentSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public TaskAttachment findById(String url, long id) {
        ResponseEntity<TaskAttachment> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskAttachment>() {});
        return response.getBody();
    }

    @Override
    public TaskAttachment create(String url, TaskAttachment entity){
        ResponseEntity<TaskAttachment> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskAttachment>() {});

        return response.getBody();
    }

    @Override
    public TaskAttachment update(String url, TaskAttachment entity){
        ResponseEntity<TaskAttachment> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskAttachment>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<TaskAttachment> findAll() {
        ResponseEntity<TaskAttachmentSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskAttachmentSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public TaskAttachment findById(long id) {
        ResponseEntity<TaskAttachment> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TaskAttachment>() {});
        return response.getBody();
    }

    @Override
    public TaskAttachment create(TaskAttachment entity){
        ResponseEntity<TaskAttachment> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskAttachment>() {});

        return response.getBody();
    }

    @Override
    public TaskAttachment update(TaskAttachment entity){
        ResponseEntity<TaskAttachment> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<TaskAttachment>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
