package com.company.example.springbootseed.utilities;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ControllerTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Getter(AccessLevel.PROTECTED) private final String urlToTest;

    protected ControllerTestBase(String urlToTest) {
        this.urlToTest = urlToTest;
    }




}
