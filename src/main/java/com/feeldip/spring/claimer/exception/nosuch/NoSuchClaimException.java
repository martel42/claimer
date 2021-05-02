package com.feeldip.spring.claimer.exception.nosuch;

public class NoSuchClaimException extends NoSuchEntityException{
    public NoSuchClaimException(String message) {
        super(message);
    }
}
