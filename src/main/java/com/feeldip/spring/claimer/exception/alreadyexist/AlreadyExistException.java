package com.feeldip.spring.claimer.exception.alreadyexist;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
