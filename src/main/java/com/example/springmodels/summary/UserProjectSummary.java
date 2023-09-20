package com.example.springmodels.summary;

import com.example.springmodels.models.UserProject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProjectSummary extends AbstractSummary<UserProject>{
}
