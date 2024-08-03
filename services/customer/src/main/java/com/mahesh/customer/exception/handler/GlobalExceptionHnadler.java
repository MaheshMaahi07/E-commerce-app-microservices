package com.mahesh.customer.exception.handler;

import com.mahesh.customer.exception.CustmerNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//By using @RestControllerAdvice, you can catch and handle exceptions in a single place, rather
//than having to repeat the same error handling code in each controller.
//This makes your code more concise, maintainable, and easier to manage.


@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHnadler {

//    When you annotate a method with @ExceptionHandler, it indicates that this method will handle
//    exceptions of a specific type. The method can then return a response to the client with an
//    appropriate error message, HTTP status code, and other relevant details.

    @ExceptionHandler(CustmerNotFoundException.class)
    public ResponseEntity<String> handle1(CustmerNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getErrorMessage());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Validation failed");
        errorResponse.setErrors(ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    return new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.toList()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
