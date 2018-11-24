package com.company.example.springbootseed.services.implementations;

import com.company.example.springbootseed.core.errorhandling.exceptions.ResourceNotFoundException;
import com.company.example.springbootseed.domain.Person;
import com.company.example.springbootseed.services.IPersonService;
import com.company.example.springbootseed.services.implementations.PersonServiceNoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
/*on the service layer we don't need to start or mock the HTTP server. We need just to invoke the java methods of the service and mock repositories*/
@ContextConfiguration(classes = PersonServiceNoRepository.class)
public class PersonServiceNoRepositoryTest {

    @Autowired
    PersonServiceNoRepository service;

    @Test
    public void shouldImplementInterface(){
        assertThat(service).isInstanceOf(IPersonService.class);
    }

    @Test
    public void shouldReturnAllPersons() {
        List<Person> persons = service.getAllPersons();

        assertThat(persons).isNotNull();
        // tests could be run in parallel. I cannot assert the number of persons if I'm going to test Delete and Create
    }

    @Test
    public void shouldRetrievePerson(){
        int personId = 1;
        Person person = service.getPersonById(personId);

        assertThat(person).isNotNull();
        assertThat(person.getId()).isEqualTo(personId);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowResourceNotFoundException_GET(){
        int personId = 10000;
        service.getPersonById(personId);
    }

    @Test
    public void shouldDeletePerson(){
        int personId = 2;
        service.deletePerson(personId);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowResourceNotFoundException_DELETE(){
        int personId = 10000;
        service.deletePerson(personId);
    }

    @Test
    public void shouldCreatePerson(){
        Person person = new Person(1, "firstName", "lastName", 85);

        Person result = service.createPerson(person);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(result.getLastName()).isEqualTo(person.getLastName());
        assertThat(result.getAge()).isEqualTo(person.getAge());
    }
}
