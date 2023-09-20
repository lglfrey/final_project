package com.example.springmodels.summary;

import com.example.springmodels.models.TaskComment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCommentSummary extends AbstractSummary<TaskComment>{
}
