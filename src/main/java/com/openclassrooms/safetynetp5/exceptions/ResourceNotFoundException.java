package com.openclassrooms.safetynetp5.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resources not found");
    }
}
