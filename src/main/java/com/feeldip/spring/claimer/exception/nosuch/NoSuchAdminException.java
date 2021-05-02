package com.feeldip.spring.claimer.exception.nosuch;

public class NoSuchAdminException extends NoSuchEntityException{
    public NoSuchAdminException(String message) {
        super(message);
    }
}
