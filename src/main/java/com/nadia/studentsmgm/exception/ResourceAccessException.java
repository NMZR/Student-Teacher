package com.nadia.studentsmgm.exception;

import jakarta.persistence.EntityNotFoundException;

public class ResourceAccessException extends EntityNotFoundException {
    public ResourceAccessException(String message){
        super(message);
    }
}
