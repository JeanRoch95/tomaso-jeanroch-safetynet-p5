package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Autowired
    private DataRepository dataRepository;

    public List<Person> getAllPersons() {
        return dataRepository.getData().getPersons();
    }

    public Person createPerson(Person person) {
        dataRepository.getData().getPersons().add(person);
        return person;
    }

    public List<Person> deletePerson(String firstName, String lastName) {
        List<Person> persons = dataRepository.getData().getPersons();
        List<Person> removedPersons = new ArrayList<>();

        Iterator<Person> iterator = persons.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                removedPersons.add(person);
                iterator.remove();
            }
        }

        return removedPersons;
    }

    public Person updatePerson(Person person, String firstName, String lastName) {
        return dataRepository.getData().getPersons().stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst()
                .map(p -> {
                    if (person.getAddress() != null) p.setAddress(person.getAddress());
                    if (person.getCity() != null) p.setCity(person.getCity());
                    if (person.getEmail() != null) p.setEmail(person.getEmail());
                    if (person.getZip() != null) p.setZip(person.getZip());
                    if (person.getPhone() != null) p.setPhone(person.getPhone());
                    return p;
                })
                .orElse(null);
    }

}
