package com.openclassrooms.safetynetp5.dto;

import com.openclassrooms.safetynetp5.model.Person;

import java.util.List;

public class ChildInfoDTO {
    private String firstName;
    private String lastName;
    private int age;
    private List<Person> members;

    public ChildInfoDTO() {
    }

    public ChildInfoDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }
}
