package com.company.example.springbootseed.controllers;

import com.company.example.springbootseed.controllers.annotations.MethodWith201;
import com.company.example.springbootseed.domain.Person;
import com.company.example.springbootseed.services.PersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons/")
@Configuration // needed to access configuration files
@PropertySource("classpath:documentation/apidocumentation.controllers.properties")// adds property files to set of properties files already loaded
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.")
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Malformed request") // valid for all
})
public class PersonController {

    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation("${controllers.personcontroller.getallpersons.description}")
    public List getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
    @ApiOperation("${controllers.personcontroller.getpersonbyid.description}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Person not found")
    })
    public Person getPersonById(@ApiParam("${controllers.personcontroller.getpersonbyid.id}")
                                @PathVariable @Valid int id) {
        return personService.getPersonById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person deleted successfully"), // placeholder not yet supported
            @ApiResponse(code = 404, message = "Person not found"),
            @ApiResponse(code = 400, message = "Malformed request")
    })
    @ApiOperation("${controllers.personcontroller.deleteperson.description}")
    public void deletePerson(@ApiParam("${controllers.personcontroller.deleteperson.id}")
                             @PathVariable @Valid int id) {
        personService.deletePerson(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ApiOperation("${controllers.personcontroller.createperson.description}")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person created successfully"),
            @ApiResponse(code = 400, message = "Malformed request")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public Person createPerson(@ApiParam("${controllers.personcontroller.createperson.person}")
                               @RequestBody @Valid Person person) {
        return personService.createPerson(person);
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
