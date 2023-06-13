package com.openclassrooms.safetynetp5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FirestationNotFoundException extends RuntimeException {
    public FirestationNotFoundException(String s) {
        super("Firestation not found : " + s);
    }
}
