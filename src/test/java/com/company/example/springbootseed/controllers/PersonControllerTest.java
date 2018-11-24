package com.company.example.springbootseed.controllers;

import com.company.example.springbootseed.Application;
import com.company.example.springbootseed.core.errorhandling.exceptions.ResourceNotFoundException;
import com.company.example.springbootseed.domain.Person;
import com.company.example.springbootseed.services.IPersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@ContextConfiguration(classes={Application.class}) // TODO: with the corret configuration it should be automatic
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // MockBean is the annotation provided by Spring that wraps mockito one
    // Annotation that can be used to add mocks to a Spring ApplicationContext.
    // If any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.
    @MockBean
    private IPersonService service;


    private static final String URL_TO_TEST = "/persons/";
    private static final int NUM_OF_PERSONS = 3;
    private static List<Person> PERSONS = createPersonList(NUM_OF_PERSONS);

    @Test
    public void shouldReturnListOfPersons() throws Exception {

        // setup
        when(service.getAllPersons()).thenReturn(PERSONS);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(URL_TO_TEST);

        // execute
        ResultActions action = this.mockMvc.perform(request);

        //print
        action.andDo(print()); // Print MvcResult details to the "standard" output stream.

        //check headers
        action.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        //check result
        MvcResult result = action.andExpect(MockMvcResultMatchers.jsonPath("$[*]", Matchers.hasSize(NUM_OF_PERSONS)))
                .andReturn();


        //parse result
        ObjectMapper mapper = new ObjectMapper();

        // this uses a TypeReference to inform Jackson about the Lists's generic type
        List<Person> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Person>>() {});

        assertThat(actual).hasSize(PERSONS.size())
                .containsOnlyElementsOf(PERSONS);

        //verify the service is invoked
        verify(service, times(1)).getAllPersons();

    }

    /* GET PERSON */

    @Test
    public void shouldReturnPerson() throws Exception {

        int personId = 1;
        Person person = createPerson(personId);
        // setup
        when(service.getPersonById(personId)).thenReturn(person);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(URL_TO_TEST + personId);

        // execute
        ResultActions action = this.mockMvc.perform(request);

        //print
        action.andDo(print()); // Print MvcResult details to the "standard" output stream.

        //check headers
        action.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        //check result
        MvcResult result = action.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(personId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("firstName"+personId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("lastName1"+personId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(21)))
                .andReturn();

        //verify the service is invoked
        verify(service, times(1)).getPersonById(personId);
    }

    @Test
    public void shouldReturnPersonNotFound() throws Exception {

        int personId = 1;
        // setup
        when(service.getPersonById(personId)).thenThrow(new ResourceNotFoundException());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(URL_TO_TEST + personId);

        // execute
        ResultActions action = this.mockMvc.perform(request);

        //print
        action.andDo(print()); // Print MvcResult details to the "standard" output stream.

        //check headers
        action.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        //verify the service is invoked
        verify(service, times(1)).getPersonById(personId);
    }

    /* DELETE */
    @Test
    public void shouldDeletePerson() throws Exception {

        int personId = 1;
                // setup
        doNothing().when(service).deletePerson(1);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(URL_TO_TEST + personId);

        // execute
        ResultActions action = this.mockMvc.perform(request);

        //print
        action.andDo(print()); // Print MvcResult details to the "standard" output stream.

        //check headers
        action.andExpect(MockMvcResultMatchers.status().isOk());

        //verify the service is invoked
        verify(service, times(1)).deletePerson(personId);
    }

    @Test
    public void shouldReturn404DeletePersonNotFound() throws Exception {

        int personId = 1;
        // setup
        doThrow(new ResourceNotFoundException()).when(service).deletePerson(1);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(URL_TO_TEST + personId);

        // execute
        ResultActions action = this.mockMvc.perform(request);

        //print
        action.andDo(print()); // Print MvcResult details to the "standard" output stream.

        //check headers
        action.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        //verify the service is invoked
        verify(service, times(1)).deletePerson(personId);
    }

    /* CREATE */
    @Test
    public void shouldCreatePerson() throws Exception {
        String requestJson = "{\"id\":1,\"firstName\":\"firstName1\",\"lastName\":\"lastName11\",\"age\":21}";
        String returnJson = "{\"id\":6,\"firstName\":\"firstName1\",\"lastName\":\"lastName11\",\"age\":21}";

        ObjectMapper mapper = new ObjectMapper();

        // this uses a TypeReference to inform Jackson about the Lists's generic type
        Person inputPerson = mapper.readValue(requestJson, new TypeReference<Person>() {});
        Person outputPerson = mapper.readValue(returnJson, new TypeReference<Person>() {});

        // setup
        when(service.createPerson(inputPerson)).thenReturn(outputPerson);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(URL_TO_TEST)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson);

        // execute
        ResultActions action = this.mockMvc.perform(request);

        //print
        action.andDo(print()); // Print MvcResult details to the "standard" output stream.

        //check headers
        action.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        //check result
        MvcResult result = action.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(outputPerson.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(outputPerson.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(outputPerson.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(outputPerson.getAge())))
                .andReturn();

        //verify the service is invoked
        verify(service, times(1)).createPerson(inputPerson);

    }

    private static List<Person> createPersonList(int n) {
        return IntStream.range(0, n).mapToObj(x -> createPerson(x)).collect(Collectors.toList());
    }

    private static Person createPerson(int id) {
        return new Person(
                id,
                "firstName" + id,
                "lastName1" + id,
                21
        );
    }

}
