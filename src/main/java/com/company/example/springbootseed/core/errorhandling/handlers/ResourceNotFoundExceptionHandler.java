package com.company.example.springbootseed.core.errorhandling.handlers;

import com.company.example.springbootseed.core.errorhandling.domain.ApiError;
import com.company.example.springbootseed.core.errorhandling.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice // Specialization of @Component for classes that declare @ExceptionHandler, @InitBinder, or @ModelAttribute methods to be shared across multiple @Controller classes.
public class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND,  ex.getLocalizedMessage(), ex);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseEntity<Object> response = new ResponseEntity<>(error, headers, HttpStatus.NOT_FOUND);

        return response;
    }
}
