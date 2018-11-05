package com.company.example.springbootseed.core.errorhandling.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RequestValidationApiSubError extends ApiSubError {
    @Getter private String object;
    @Getter private String field;
    @Getter private Object rejectedValue;
    @Getter private String message;

    public RequestValidationApiSubError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
