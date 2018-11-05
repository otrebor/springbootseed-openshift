package com.company.example.springbootseed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

// @SpringBootApplication includes the following three annotations
@Configuration /*tags the class as a source of bean definitions for the application context.*/
@ComponentScan /*tells Spring to look for other components, configurations, and services in the this package, allowing it to find the controllers.*/
@EnableAutoConfiguration /* tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. */
public class Application extends SpringBootServletInitializer {

    Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Class[] sources = {Application.class};
        SpringApplication.run(sources, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // Customize the application or call application.sources(...) to add sources
        logger.info("Servlet Initialization configuration executed");
        return application.sources(Application.class);
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
