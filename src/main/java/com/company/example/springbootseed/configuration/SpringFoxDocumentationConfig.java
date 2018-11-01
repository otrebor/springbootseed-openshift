package com.company.example.springbootseed.configuration;

import com.company.example.springbootseed.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxDocumentationConfig {

    @Autowired
    private IApplicationInfoProperties appInfoProperties;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(Application.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                appInfoProperties.getTitle(),
                appInfoProperties.getDescription(),
                appInfoProperties.getVersion(),
                appInfoProperties.getTermsOfServiceUrl(),
                getContactDetails(),
                appInfoProperties.getLicence(),
                appInfoProperties.getLicenceUrl(),
                Collections.emptyList()
        );
    }

    private Contact getContactDetails() {
        return new Contact(appInfoProperties.getContactName(),
                appInfoProperties.getContactUrl(),
                appInfoProperties.getContactEmail());
    }
}
