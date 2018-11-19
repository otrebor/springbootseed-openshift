package com.company.example.springbootseed.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Environment {
    Local ("local"), // local (devs computer)
    Dev ("dev"),
    Integration ("integration"),
    PreProduction ("preprod"),
    Production ("prod");

    private final String value;

    private Environment(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Environment getEnvoirment(String value) {
        for (Environment level : Environment.values()) {
            if(level.getValue().toLowerCase().equals(value.toLowerCase())){
                return level;
            }
        }
        throw new IllegalArgumentException(String.format("Value %s as Environment not recognized", value));
    }
}
