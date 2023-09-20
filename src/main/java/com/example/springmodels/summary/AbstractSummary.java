package com.example.springmodels.summary;

import com.example.springmodels.models.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractSummary<T extends GenericEntity<T>> {
    private List<T> content;
}
