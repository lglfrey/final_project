package com.example.springmodels.summary;

import com.example.springmodels.models.UserActivityLog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserActivityLogSummary extends AbstractSummary<UserActivityLog> {
}
