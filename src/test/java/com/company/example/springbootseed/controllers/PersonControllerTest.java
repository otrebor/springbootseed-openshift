package com.company.example.springbootseed.controllers;

import com.company.example.springbootseed.Application;
import com.company.example.springbootseed.domain.Person;
import com.company.example.springbootseed.services.PersonService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@ContextConfiguration(classes={Application.class}) // TODO: with the corret configuration it should be automatic
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;


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
        action.andDo(MockMvcResultHandlers.print()); // Print MvcResult details to the "standard" output stream.

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
