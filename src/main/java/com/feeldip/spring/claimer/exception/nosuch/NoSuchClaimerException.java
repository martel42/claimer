package com.feeldip.spring.claimer.exception.nosuch;

public class NoSuchClaimerException extends NoSuchEntityException{
    public NoSuchClaimerException(String message) {
        super(message);
    }
}
