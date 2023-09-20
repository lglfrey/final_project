package com.example.springmodels.clients;

import com.example.springmodels.api.ApiHelper;
import com.example.springmodels.api.GenericRestClient;
import com.example.springmodels.models.UserActivityLog;
import com.example.springmodels.summary.UserActivityLogSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserActivityLogClient extends GenericRestClient<UserActivityLog, UserActivityLogSummary> {
    public UserActivityLogClient() {
        super(ApiHelper.getUser_Activity_LogUrl());
    }
    @Override
    public List<UserActivityLog> findAll(String url) {
        ResponseEntity<UserActivityLogSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserActivityLogSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public UserActivityLog findById(String url, long id) {
        ResponseEntity<UserActivityLog> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserActivityLog>() {});
        return response.getBody();
    }

    @Override
    public UserActivityLog create(String url, UserActivityLog entity){
        ResponseEntity<UserActivityLog> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserActivityLog>() {});

        return response.getBody();
    }

    @Override
    public UserActivityLog update(String url, UserActivityLog entity){
        ResponseEntity<UserActivityLog> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserActivityLog>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<UserActivityLog> findAll() {
        ResponseEntity<UserActivityLogSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserActivityLogSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public UserActivityLog findById(long id) {
        ResponseEntity<UserActivityLog> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserActivityLog>() {});
        return response.getBody();
    }

    @Override
    public UserActivityLog create(UserActivityLog entity){
        ResponseEntity<UserActivityLog> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserActivityLog>() {});

        return response.getBody();
    }

    @Override
    public UserActivityLog update(UserActivityLog entity){
        ResponseEntity<UserActivityLog> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<UserActivityLog>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
