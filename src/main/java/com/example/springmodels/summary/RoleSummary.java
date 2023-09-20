package com.example.springmodels.summary;

import com.example.springmodels.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleSummary extends AbstractSummary<Role> {
}
