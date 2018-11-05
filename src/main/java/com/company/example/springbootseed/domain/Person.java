package com.company.example.springbootseed.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.*;

@Configuration // needed to access configuration files
@PropertySource("classpath:documentation/apidocumentation.domain.properties")// adds property files to set of properties files already loaded
@ApiModel(description = "Class representing a person tracked by the application.") // placeholders not yet supported in this annotation
@EqualsAndHashCode
public class Person {

    // position helps in specifying the order of parameters in documentation
    // placeholder from configuration file NOTE: NOT ALL THE ANNOTATIONS ARE SUPPORTED
    @ApiModelProperty(notes = "${domain.person.id.notes}", example = "1", required = true, position = 0)
    @NotNull
    private int id;

    @ApiModelProperty(notes = "${domain.person.firstName.notes}", example = "John", required = true, position = 1)
    @NotBlank
    @Size(min = 1, max = 20)
    private String firstName;

    @ApiModelProperty(notes = "${domain.person.lastName.notes}", example = "Doe", required = true, position = 2)
    @NotBlank
    //@Pattern(regexp ="[SOME REGULAR EXPRESSION]")
    private String lastName;

    @ApiModelProperty(notes = "${domain.person.age.notes}", example = "42", position = 3)
    @Min(0)
    @Max(100)
    private int age;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
