package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.stereotype.Service;

import java .util.List;
@Service
public interface PersonService {

    List<Person> getAllPersons();

    Person createPerson(Person person);

    List<Person> deletePerson(String firstName, String lastName);

    Person updatePerson(Person person, String firstName, String lastName);
}

