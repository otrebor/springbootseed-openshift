package com.company.example.springbootseed.controllers;

import com.company.example.springbootseed.configuration.IApplicationInfoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck/")
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);
    private static final String LOGGING_MESSAGE = "Health check invoked.";
    @Autowired
    private IApplicationInfoProperties appProperties;

    @RequestMapping(method = RequestMethod.GET)
    public String healthCheck(){
        // used to check the logger level enabled
        if(logger.isTraceEnabled()) {
            logger.trace(LOGGING_MESSAGE + appProperties.toString());
        } else if(logger.isDebugEnabled()){
            logger.debug(LOGGING_MESSAGE + appProperties.toString());
        } else if(logger.isInfoEnabled()) {
            logger.info(LOGGING_MESSAGE + appProperties.toString());
        } else if(logger.isWarnEnabled()) {
            logger.warn(LOGGING_MESSAGE + appProperties.toString());
        } else if(logger.isErrorEnabled()){
            logger.error(LOGGING_MESSAGE + appProperties.toString());
        }

        return ("Hello, application is running on environment \"" + appProperties.getEnvironment().getValue() +"\"" );
    }
}
