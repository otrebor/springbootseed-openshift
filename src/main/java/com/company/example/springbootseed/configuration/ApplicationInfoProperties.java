package com.company.example.springbootseed.configuration;

import com.company.example.springbootseed.core.domain.Environment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties("app.info")
@Validated //enables validation of fields - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
@ToString // automatically generates toString
public class ApplicationInfoProperties implements IApplicationInfoProperties {

    @Getter @Setter @NotNull @NotBlank private String name;
    @Getter @NotNull private Environment environment;

    @Getter @Setter @NotNull @NotBlank private String title;
    @Getter @Setter @NotNull @NotBlank private String description;
    @Getter @Setter @NotNull @NotBlank private String version;
    @Getter @Setter @NotNull @NotBlank private String termsOfServiceUrl;
    @Getter @Setter @NotNull @NotBlank private String contactName;
    @Getter @Setter @NotNull @NotBlank private String contactUrl;
    @Getter @Setter @NotNull @NotBlank private String contactEmail;
    @Getter @Setter @NotNull @NotBlank private String licence;
    @Getter @Setter @NotNull @NotBlank private String licenceUrl;

    public void setEnvironment(String environment) {
        this.environment = Environment.getEnvoirment(environment);
    }
}
