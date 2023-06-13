package com.openclassrooms.safetynetp5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonCreateException extends RuntimeException {
    public PersonCreateException(String s) {
        super(s);
    }
}
