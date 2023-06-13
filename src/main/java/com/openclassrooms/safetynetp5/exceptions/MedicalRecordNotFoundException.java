package com.openclassrooms.safetynetp5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class MedicalRecordNotFoundException extends RuntimeException {

    public MedicalRecordNotFoundException(String s) {
        super("Medical record(s) not found : " + s);
    }
}
