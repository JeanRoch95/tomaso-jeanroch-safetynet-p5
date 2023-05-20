package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonRepository {

    List<Person> getAllPersons();

    Person createPerson(Person person);

    List<Person> deletePerson(String firstName, String lastName);

    Person updatePerson(Person person, String firstname, String lastname);

}
