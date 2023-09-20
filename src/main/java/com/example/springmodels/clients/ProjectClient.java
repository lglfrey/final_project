package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.Project;
import com.example.springmodels.models.Project;
import com.example.springmodels.summary.ProjectSummary;
import com.example.springmodels.summary.ProjectSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProjectClient extends GenericRestClient<Project, ProjectSummary> {
    public ProjectClient() {
        super(ApiHelper.getProjectUrl());
    }
    @Override
    public List<Project> findAll(String url) {
        ResponseEntity<ProjectSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ProjectSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Project findById(String url, long id) {
        ResponseEntity<Project> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Project>() {});
        return response.getBody();
    }

    @Override
    public Project create(String url, Project entity){
        ResponseEntity<Project> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Project>() {});

        return response.getBody();
    }

    @Override
    public Project update(String url, Project entity){
        ResponseEntity<Project> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Project>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<Project> findAll() {
        ResponseEntity<ProjectSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ProjectSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Project findById(long id) {
        ResponseEntity<Project> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Project>() {});
        return response.getBody();
    }

    @Override
    public Project create(Project entity){
        ResponseEntity<Project> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Project>() {});

        return response.getBody();
    }

    @Override
    public Project update(Project entity){
        ResponseEntity<Project> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Project>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
