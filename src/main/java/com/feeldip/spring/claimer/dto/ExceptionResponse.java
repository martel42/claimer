package com.feeldip.spring.claimer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ExceptionResponse {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ExceptionResponse(String message, String debugMessage, List<String> errors) {
        this.message = message;
        this.debugMessage = debugMessage;
        this.errors = errors;
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
