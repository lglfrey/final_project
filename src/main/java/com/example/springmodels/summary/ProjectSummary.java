package com.example.springmodels.summary;

import com.example.springmodels.models.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectSummary extends AbstractSummary<Project> {
}
