package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonRepository {

    List<Person> getAll();

    Person save(Person person);

    List<Person> delete(String firstName, String lastName);

    Person update(Person person, String firstname, String lastname);

}
