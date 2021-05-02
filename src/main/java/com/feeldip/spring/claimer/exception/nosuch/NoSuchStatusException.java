package com.feeldip.spring.claimer.exception.nosuch;

public class NoSuchStatusException extends NoSuchEntityException{
    public NoSuchStatusException(String message) {
        super(message);
    }
}
