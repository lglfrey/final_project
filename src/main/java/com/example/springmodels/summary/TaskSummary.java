package com.example.springmodels.summary;

import com.example.springmodels.models.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSummary extends AbstractSummary<Task>{
}
