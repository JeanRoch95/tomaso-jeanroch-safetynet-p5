package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAll();
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
    @Override
    public List<Person> deletePerson(String firstName, String lastName) {
        return personRepository.delete(firstName, lastName);
    }
    @Override
    public Person updatePerson(Person person, String firstName, String lastName) {
        return personRepository.update(person, firstName, lastName);
    }
}
