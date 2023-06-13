package com.openclassrooms.safetynetp5.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalRecord {

    private String firstName;

    private String lastName;
    @JsonFormat(pattern = "dd/MM/YYYY")
    private Date birthdate;

    private List<String> medications;

    private List<String> allergies;

    public MedicalRecord(){
    }

    public MedicalRecord(String firstName, String lastName, Date birthdate, List<String> medication, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (birthdate == null) {
            this.birthdate = null;
        } else {
            this.birthdate = new Date(birthdate.getTime());
        };
        this.medications = medication;
        this.allergies = allergies;
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

    public Date getBirthdate() {
        Date date = birthdate;
        return date;
    }

    public void setBirthdate(Date birthdate) {
        if (birthdate == null) {
            this.birthdate = null;
        } else {
            this.birthdate = new Date(birthdate.getTime());
        }
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

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
