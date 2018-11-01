package com.company.example.springbootseed.controllers;

import com.company.example.springbootseed.configuration.IApplicationInfoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log/")
public class LoggingController {

    @Autowired
    private IApplicationInfoProperties appProperties;


    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        logger.trace("A TRACE Message " + appProperties.getName());
        logger.debug("A DEBUG Message"+ appProperties.getName());
        logger.info("An INFO Message"+ appProperties.getName());
        logger.warn("A WARN Message"+ appProperties.getName());
        logger.error("An ERROR Message"+ appProperties.getName());

        return "Howdy! Check out the Logs to see the output... " + appProperties.getName();
    }
}
