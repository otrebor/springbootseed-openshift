package com.company.example.springbootseed.errorhandling.utilities.implementation;

import com.company.example.springbootseed.errorhandling.domain.ApiError;
import com.company.example.springbootseed.errorhandling.domain.ApiSubError;
import com.company.example.springbootseed.errorhandling.domain.RequestNotReadableApiSubError;
import com.company.example.springbootseed.errorhandling.utilities.IHttpMessageNotReadableExceptionToApiErrorConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration // needed to access configuration files
@PropertySource("classpath:text/errors.api.properties")// adds property files to set of properties files already loaded
public class HttpMessageNotReadableExceptionToApiErrorConverter implements IHttpMessageNotReadableExceptionToApiErrorConverter {

    @Value("${errors.api.message_not_readable}")
    private String ERROR_MESSAGE;

    @Override
    public ApiError convert(HttpMessageNotReadableException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ERROR_MESSAGE, ex);
        error.setSubErrors(this.generateSubErrors(ex));
        return error;
    }

    private Collection<? extends ApiSubError> generateSubErrors(HttpMessageNotReadableException ex){
        List<RequestNotReadableApiSubError> subErrors = new ArrayList<RequestNotReadableApiSubError>();
        String message = ex.getLocalizedMessage();
        subErrors.add(new RequestNotReadableApiSubError(message));
        return subErrors;
    }
}
