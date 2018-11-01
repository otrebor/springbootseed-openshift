package com.company.example.springbootseed.errorhandling.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;

@ToString(includeFieldNames=true)
public class ApiError {

    private static final Logger logger = LoggerFactory.getLogger(ApiError.class);
    @Getter(AccessLevel.PUBLIC) // getter necessary for serialization
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    @Getter() private String message;
    private String debugMessage;
    @Getter @Setter() private Collection<? extends ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
        logger.error(String.format("HttpStatus: %d -> %s", status.value(), status.getReasonPhrase()));
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this(status);
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
        logger.error(message, ex);
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(status);
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
        logger.error(message, ex);
    }
}
