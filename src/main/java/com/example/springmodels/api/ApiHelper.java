package com.example.springmodels.api;

import lombok.Getter;

@Getter
public class ApiHelper {
    private static final String DEFAULT_URL = "http://localhost:8080/api/public/";
    private static final String DEFAULT_URL_USERS = DEFAULT_URL + "users";
    private static final String DEFAULT_URL_PROJECT = DEFAULT_URL + "projects";
    private static final String DEFAULT_URL_PROJECT_USERS = DEFAULT_URL + "user_projects";
    private static final String DEFAULT_URL_TASK_ATTACHMENT = DEFAULT_URL + "task_attachments";
    private static final String DEFAULT_URL_TASK_CATEGORY = DEFAULT_URL + "task_categories";
    private static final String DEFAULT_URL_TASK_COMMENT = DEFAULT_URL + "task_comments";
    private static final String DEFAULT_URL_TASK = DEFAULT_URL + "tasks";
    private static final String DEFAULT_URL_USER_ACTIVITY_LOG = DEFAULT_URL + "user_activity_logs";
    private static final String DEFAULT_URL_ROLES = DEFAULT_URL + "roles";



    public static String getDefaultUrl() {
        return DEFAULT_URL;
    }
    public static String getUsersUrl() {
        return DEFAULT_URL_USERS;
    }
    public static String getProjectUrl() {
        return DEFAULT_URL_PROJECT;
    }
    public static String getUsers_ProjectUrl() {
        return DEFAULT_URL_PROJECT;
    }
    public static String getTask_AttachmentUrl() {
        return DEFAULT_URL_TASK_ATTACHMENT;
    }
    public static String getTask_CategoryUrl() {
        return DEFAULT_URL_TASK_CATEGORY;
    }
    public static String getTask_CommentUrl() {
        return DEFAULT_URL_TASK_COMMENT;
    }
    public static String getTaskUrl() {
        return DEFAULT_URL_TASK;
    }
    public static String getUser_Activity_LogUrl() {
        return DEFAULT_URL_USER_ACTIVITY_LOG;
    }
    public static String getRolesUrl() {
        return DEFAULT_URL_ROLES;
    }
}
