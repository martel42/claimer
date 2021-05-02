package com.feeldip.spring.claimer.exception.nosuch;

public class NoSuchTypeException extends NoSuchEntityException{
    public NoSuchTypeException(String message) {
        super(message);
    }
}
