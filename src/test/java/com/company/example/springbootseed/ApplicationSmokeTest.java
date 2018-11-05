package com.company.example.springbootseed;

import com.company.example.springbootseed.controllers.HealthCheckController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
/* tells Spring Boot to go and look for a main configuration class (one with @SpringBootApplication),
and use that to start a Spring application context */
@SpringBootTest(classes={Application.class}) // TODO: with the corret configuration it should be automatic
public class ApplicationSmokeTest {

    @Autowired
    private HealthCheckController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
