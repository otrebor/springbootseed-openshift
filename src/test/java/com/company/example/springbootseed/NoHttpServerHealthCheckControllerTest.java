package com.company.example.springbootseed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class}) // TODO: with the corret configuration it should be automatic
@AutoConfigureMockMvc
public class NoHttpServerHealthCheckControllerTest {

/*In this test, the full Spring application context is started, but without the server.*/

    private static final String URL_TO_TEST = "/healthcheck/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get(URL_TO_TEST)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, application is running on environment")));
    }
}
