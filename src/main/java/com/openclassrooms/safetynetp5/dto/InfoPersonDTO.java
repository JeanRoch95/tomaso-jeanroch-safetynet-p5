package com.openclassrooms.safetynetp5.dto;

import java.util.List;

public class InfoPersonDTO {

    private String lastName;

    private String age;

    private List<String> medications;

    private List<String> allergies;

    public InfoPersonDTO() {
    }

    public InfoPersonDTO(String lastName, String age, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
