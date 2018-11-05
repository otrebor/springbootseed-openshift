package com.company.example.springbootseed;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes={Application.class}) // TODO: with the corret configuration it should be automatic
public class HttpServerHealthCheckControllerTest {
/* start the application up and listen for a connection like it would do in production,
    and then send an HTTP request and assert the response.*/

    private static final String URL_TO_TEST = "/healthcheck/";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + URL_TO_TEST,
                String.class)).contains("Hello, application is running on environment");
    }
}
