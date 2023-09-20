package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.UserProject;
import com.example.springmodels.summary.UserProjectSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserProjectClient extends GenericRestClient<UserProject, UserProjectSummary> {
    public UserProjectClient() {
        super(ApiHelper.getUsers_ProjectUrl());
    }
    @Override
    public List<UserProject> findAll(String url) {
        ResponseEntity<UserProjectSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserProjectSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public UserProject findById(String url, long id) {
        ResponseEntity<UserProject> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserProject>() {});
        return response.getBody();
    }

    @Override
    public UserProject create(String url, UserProject entity){
        ResponseEntity<UserProject> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserProject>() {});

        return response.getBody();
    }

    @Override
    public UserProject update(String url, UserProject entity){
        ResponseEntity<UserProject> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserProject>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<UserProject> findAll() {
        ResponseEntity<UserProjectSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserProjectSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public UserProject findById(long id) {
        ResponseEntity<UserProject> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserProject>() {});
        return response.getBody();
    }

    @Override
    public UserProject create(UserProject entity){
        ResponseEntity<UserProject> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserProject>() {});

        return response.getBody();
    }

    @Override
    public UserProject update(UserProject entity){
        ResponseEntity<UserProject> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserProject>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
