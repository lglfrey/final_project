package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.modelUser;
import com.example.springmodels.summary.modelUserSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserClient extends GenericRestClient<modelUser, modelUserSummary> {
    public UserClient() {
        super(ApiHelper.getUsersUrl());
    }

    @Override
    public List<modelUser> findAll(String url) {
        ResponseEntity<modelUserSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<modelUserSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public modelUser findById(String url, long id) {
        ResponseEntity<modelUser> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<modelUser>() {});
        return response.getBody();
    }

    @Override
    public modelUser create(String url, modelUser entity){
        ResponseEntity<modelUser> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<modelUser>() {});

        return response.getBody();
    }

    @Override
    public modelUser update(String url, modelUser entity){
        ResponseEntity<modelUser> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<modelUser>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<modelUser> findAll() {
        ResponseEntity<modelUserSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<modelUserSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public modelUser findById(long id) {
        ResponseEntity<modelUser> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<modelUser>() {});
        return response.getBody();
    }

    @Override
    public modelUser create(modelUser entity){
        ResponseEntity<modelUser> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<modelUser>() {});

        return response.getBody();
    }

    @Override
    public modelUser update(modelUser entity){
        ResponseEntity<modelUser> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<modelUser>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
