package com.company.example.springbootseed.configuration;

import com.company.example.springbootseed.core.domain.Environment;

public interface IApplicationInfoProperties {
    String getName();
    Environment getEnvironment();
    String getTitle();
    String getDescription() ;
    String getVersion();
    String getTermsOfServiceUrl() ;
    String getContactName();
    String getContactUrl();
    String getContactEmail();
    String getLicence() ;
    String getLicenceUrl();
}
