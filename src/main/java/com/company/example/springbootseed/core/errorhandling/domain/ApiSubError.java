package com.company.example.springbootseed.core.errorhandling.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RequestNotReadableApiSubError.class, name = "requestNotReadable"),
        @JsonSubTypes.Type(value = RequestValidationApiSubError.class, name = "RequestValidationFailed")
})
public abstract class ApiSubError {
}
