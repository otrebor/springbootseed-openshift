package com.company.example.springbootseed.controllers;

import com.company.example.springbootseed.configuration.IApplicationInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello/")
public class HelloWorldController {

    @Autowired
    private IApplicationInfoProperties appProperties;

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(){
        return ("Hello, SpringBoot running:" + appProperties.toString() );
    }
}
