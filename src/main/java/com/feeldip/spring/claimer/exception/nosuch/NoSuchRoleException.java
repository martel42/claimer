package com.feeldip.spring.claimer.exception.nosuch;

public class NoSuchRoleException extends NoSuchEntityException{
    public NoSuchRoleException(String message) {
        super(message);
    }
}
