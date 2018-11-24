package com.company.example.springbootseed.services;

import com.company.example.springbootseed.domain.Person;

import java.util.List;

public interface IPersonService {

    List<Person> getAllPersons();

    Person getPersonById(int id) ;

    Person createPerson(Person person);

    void deletePerson(int id);
}
