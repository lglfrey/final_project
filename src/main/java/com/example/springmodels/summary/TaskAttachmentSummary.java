package com.example.springmodels.summary;
import com.example.springmodels.models.TaskAttachment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskAttachmentSummary extends AbstractSummary<TaskAttachment>{
}
