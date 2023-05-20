package com.openclassrooms.safetynetp5.model;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Data {

    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
