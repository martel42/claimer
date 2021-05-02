package com.feeldip.spring.claimer.exception.nosuch;

import java.util.NoSuchElementException;

public class NoSuchEntityException extends NoSuchElementException {
    public NoSuchEntityException(String message) {
        super(message);
    }
}
