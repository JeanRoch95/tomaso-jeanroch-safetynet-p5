package com.openclassrooms.safetynetp5.model;

import java.util.List;

public class Data {

    private List<Person> persons;

    private List<Firestation> firestations;

    private List<MedicalRecord> medicalRecord;


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Firestation> getFirestations() { return firestations; }

    public void setFirestations (List<Firestation> firestations) { this.firestations = firestations; }

    public List<MedicalRecord> getMedicalRecords() { return medicalRecord; }

    public void setMedicalRecord(List<MedicalRecord> medicalRecords) {
        this.medicalRecord = medicalRecords;
    }

}
