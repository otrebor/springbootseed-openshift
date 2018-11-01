package com.company.example.springbootseed.errorhandling.utilities.implementation;

import com.company.example.springbootseed.errorhandling.domain.ApiError;
import com.company.example.springbootseed.errorhandling.domain.ApiSubError;
import com.company.example.springbootseed.errorhandling.domain.RequestValidationApiSubError;
import com.company.example.springbootseed.errorhandling.utilities.IMethodArgumentNotValidExceptionToApiErrorConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration // needed to access configuration files
@PropertySource("classpath:text/errors.api.properties")// adds property files to set of properties files already loaded
public class MethodArgumentNotValidExceptionToApiErrorConverter implements IMethodArgumentNotValidExceptionToApiErrorConverter {

    @Value("${errors.api.validation}")
    private String ERROR_MESSAGE_VALIDATION;

    @Override
    public ApiError convert(MethodArgumentNotValidException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ERROR_MESSAGE_VALIDATION, ex);
        error.setSubErrors(this.generateSubErrors(ex));
        return error;
    }

    private Collection<? extends ApiSubError> generateSubErrors(MethodArgumentNotValidException ex){
        List<RequestValidationApiSubError> subErrors = new ArrayList<RequestValidationApiSubError>();
        List<FieldError> validationErrors  = ex.getBindingResult().getFieldErrors();
        for(FieldError error : validationErrors){

            String object = error.getObjectName();
            String fieldName = error.getField();
            Object rejectedValue = error.getRejectedValue();
            String message = error.getDefaultMessage();
            RequestValidationApiSubError subError = new RequestValidationApiSubError(object, fieldName, rejectedValue, message);

            subErrors.add(subError);
        }
        return subErrors;
    }
}
