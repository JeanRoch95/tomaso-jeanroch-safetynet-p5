package com.openclassrooms.safetynetp5.exceptions;


public class ArgumentNotFoundException extends RuntimeException {
    public ArgumentNotFoundException() {
        super("Argument not found");
    }
}
