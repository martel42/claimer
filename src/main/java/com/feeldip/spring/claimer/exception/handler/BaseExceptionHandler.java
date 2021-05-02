package com.feeldip.spring.claimer.exception.handler;

import com.feeldip.spring.claimer.exception.alreadyexist.AlreadyExistException;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchEntityException;
import com.feeldip.spring.claimer.dto.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleAlreadyExistException(AlreadyExistException exception) {
        ExceptionResponse data = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleNoSuchEntityException(NoSuchEntityException exception) {
        ExceptionResponse data = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponse response = new ExceptionResponse("Method arguments are not valid", ex.getMessage(), errors);
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse("Malformed JSON Request", ex.getMessage());
        return new ResponseEntity(response, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception exception) {
        ExceptionResponse response = new ExceptionResponse("An unexpected error has occurred"
                , exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
