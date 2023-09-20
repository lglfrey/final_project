package com.example.springmodels.summary;

import com.example.springmodels.models.modelUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class modelUserSummary extends AbstractSummary<modelUser>{
}
