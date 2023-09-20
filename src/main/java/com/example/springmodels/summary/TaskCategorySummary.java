package com.example.springmodels.summary;

import com.example.springmodels.models.TaskCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCategorySummary extends AbstractSummary<TaskCategory>{
}
