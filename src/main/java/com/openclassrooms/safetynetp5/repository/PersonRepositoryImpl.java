package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final List<Person> persons = new ArrayList<>();

    private final DataRepository dataRepository;

    public PersonRepositoryImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Person> getAll() {
        return dataRepository.getData().getPersons();
    }

    public Person save(Person person) {
        dataRepository.getData().getPersons().add(person);
        return person;
    }

    public List<Person> delete(String firstName, String lastName) {
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

    public Person update(Person person, String firstName, String lastName) {
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

    @Override
    public List<Person> findPersonByAddress(String address) {
        List<Person> personByAddress = new ArrayList<>();
        List<Person> persons = dataRepository.getData().getPersons();

        for (Person p : persons) {
            if (p.getAddress().contentEquals(address)) {
                personByAddress.add(p);
            }
        }
        return personByAddress;
    }

    public List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName) {
        List<Person> personByFirstNameAndLastname = new ArrayList<>();

        List<Person> personList = dataRepository.getData().getPersons();

        for(Person p : personList) {
            if(p.getFirstName().contentEquals(firstName) && p.getLastName().contentEquals(lastName)) {
                personByFirstNameAndLastname.add(p);
            }
        }
        return personByFirstNameAndLastname;
    }

    public List<Person> findPersonByLastName(String lastName) {
        List<Person> personByLastname = new ArrayList<>();

        List<Person> personList = dataRepository.getData().getPersons();

        for(Person p : personList) {
            if(p.getLastName().contentEquals(lastName)) {
                personByLastname.add(p);
            }
        }
        return personByLastname;
    }

}
