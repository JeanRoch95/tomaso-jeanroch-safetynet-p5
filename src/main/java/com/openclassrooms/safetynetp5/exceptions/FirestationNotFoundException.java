package com.openclassrooms.safetynetp5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FirestationNotFoundException extends RuntimeException {
    public FirestationNotFoundException() {
        super("Firestation not found");
    }
}
