package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public List<Person> read() {
        return personRepository.getAllPersons();
    }

    @Override
    public Person save(Person person) {
        return personRepository.createPerson(person);
    }
    @Override
    public List<Person> delete(String firstName, String lastName) {
        return personRepository.deletePerson(firstName, lastName);
    }
    @Override
    public Person updatePerson(Person person, String firstName, String lastName) {
        return personRepository.updatePerson(person, firstName, lastName);
    }
}
