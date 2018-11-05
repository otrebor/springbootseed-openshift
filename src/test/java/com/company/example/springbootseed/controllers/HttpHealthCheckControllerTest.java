package com.company.example.springbootseed.controllers;

import com.company.example.springbootseed.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HealthCheckController.class)
@ContextConfiguration(classes={Application.class}) // TODO: with the corret configuration it should be automatic
public class HttpHealthCheckControllerTest {
/*here Spring Boot is only instantiating the web layer, not the whole context.
In an application with multiple controllers you can even ask for just one to be instantiated,
using, for example @WebMvcTest(HomeController.class) */
        private static final String URL_TO_TEST = "/healthcheck/";

        @Autowired
        private MockMvc mockMvc;


        @Test
        public void greetingShouldReturnMessageFromService() throws Exception {

            // setup
            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(URL_TO_TEST);

            // execute
            ResultActions action = this.mockMvc.perform(request);

            // check
            action.andDo(print()) // Print MvcResult details to the "standard" output stream.
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello, application is running on environment")));
        }


}
