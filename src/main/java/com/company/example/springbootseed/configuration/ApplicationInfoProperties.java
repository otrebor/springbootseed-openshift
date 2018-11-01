package com.company.example.springbootseed.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.info")
public class ApplicationInfoProperties implements IApplicationInfoProperties {

    private String name;
    private String environment;
    private String title;
    private String description;
    private String version;
    private String termsOfServiceUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String licence;
    private String licenceUrl;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        checkRequiredString(environment);
        this.environment = environment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        checkRequiredString(description);
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        checkRequiredString(version);
        this.version = version;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfService) {
        checkRequiredString(termsOfService);
        this.termsOfServiceUrl = termsOfService;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        checkRequiredString(contactName);
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        checkRequiredString(contactUrl);
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        checkRequiredString(contactEmail);
        this.contactEmail = contactEmail;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        checkRequiredString(licence);
        this.licence = licence;
    }

    public String getLicenceUrl() {
        return licenceUrl;
    }

    public void setLicenceUrl(String licenceUrl) {
        checkRequiredString(licenceUrl);
        this.licenceUrl = licenceUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkRequiredString(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "ApplicationInfoProperties{" +
                "name='" + name + '\'' +
                ", environment='" + environment + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactUrl='" + contactUrl + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", licence='" + licence + '\'' +
                ", licenceUrl='" + licenceUrl + '\'' +
                '}';
    }

    private void checkRequiredString(String value){
        if(value == null){
            throw new IllegalStateException("Configuration files not properly set");
        }
    }
}
