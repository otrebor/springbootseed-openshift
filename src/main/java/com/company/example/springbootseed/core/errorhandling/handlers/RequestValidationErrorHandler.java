package com.company.example.springbootseed.core.errorhandling.handlers;

import com.company.example.springbootseed.core.errorhandling.domain.ApiError;
import com.company.example.springbootseed.core.errorhandling.utilities.IHttpMessageNotReadableExceptionToApiErrorConverter;
import com.company.example.springbootseed.core.errorhandling.utilities.IMethodArgumentNotValidExceptionToApiErrorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice // It is used to enable a single ExceptionHandler to be applied to multiple controllers.
public class RequestValidationErrorHandler extends ResponseEntityExceptionHandler {

    private final IMethodArgumentNotValidExceptionToApiErrorConverter validationConverter;
    private final IHttpMessageNotReadableExceptionToApiErrorConverter notReadableConverter;

    @Autowired
    public RequestValidationErrorHandler(IMethodArgumentNotValidExceptionToApiErrorConverter validationConverter,
                                         IHttpMessageNotReadableExceptionToApiErrorConverter notReadableConverter){
        this.validationConverter = validationConverter;
        this.notReadableConverter = notReadableConverter;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.buildResponseEntity(validationConverter.convert(ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.buildResponseEntity(this.notReadableConverter.convert(ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }
}

