package com.company.example.springbootseed.core.errorhandling.utilities;

import org.springframework.http.converter.HttpMessageNotReadableException;

public interface IHttpMessageNotReadableExceptionToApiErrorConverter extends IExceptionToApiErrorConverter<HttpMessageNotReadableException> {
}
