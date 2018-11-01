package com.company.example.springbootseed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /* It retrieves all the beans that were created either by your app or were automatically added thanks to Spring Boot. It sorts them and prints them out. */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            StringBuilder builder = new StringBuilder();

            builder.append("Let's inspect the beans provided by Spring Boot:\n");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                builder.append(beanName);
                builder.append("\n");
            }

            logger.info(builder.toString());
        };
    }
}
