package com.openclassrooms.safetynetp5.exceptions;

public class MedicalRecordNotFoundException extends RuntimeException {

    public MedicalRecordNotFoundException() {
        super("Medical record(s) not found : ");
    }
}
