package com.iagl.aviospoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

// Controller advice that handles exceptions globally across the whole application
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for AirportNotFoundException
    // Returns a response with status BAD_REQUEST and the exception message as the body
    @ExceptionHandler(AirportNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleAirportNotFoundException(AirportNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // Exception handler for CabinCodeNotFoundException
    // Returns a response with status BAD_REQUEST and the exception message as the body
    @ExceptionHandler(CabinCodeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleAirportNotFoundException(CabinCodeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // Exception handler for MethodArgumentNotValidException
    // Returns a response with status BAD_REQUEST and the validation error messages as the body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        // Collect all field validation errors into a single string
        String errors = e.getBindingResult()
                .getAllErrors().stream()
                .map(objectError -> {
                    if (objectError instanceof FieldError) {
                        return String.format("%s: %s", ((FieldError) objectError).getField(), objectError.getDefaultMessage());
                    } else {
                        return objectError.getDefaultMessage();
                    }
                })
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
