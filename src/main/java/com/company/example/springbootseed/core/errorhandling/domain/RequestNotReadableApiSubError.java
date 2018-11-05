package com.company.example.springbootseed.core.errorhandling.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RequestNotReadableApiSubError extends ApiSubError {
    @Getter private String message;

}
