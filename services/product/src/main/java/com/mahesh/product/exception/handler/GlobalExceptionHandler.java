package com.mahesh.product.exception.handler;

import com.mahesh.product.exception.FourNotFour;
import com.mahesh.product.exception.ProductAlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductAlreadyExistException.class})
    public ResponseEntity<String> handleProductException(ProductAlreadyExistException exception){
        return ResponseEntity.status((HttpStatus.ALREADY_REPORTED))
                .body(exception.getErrorMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public  ResponseEntity<String> handle(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(FourNotFour.class)
    public ResponseEntity<String> handleFourNotFour(FourNotFour exception){
        return ResponseEntity.status((HttpStatus.NOT_FOUND))
                .body(exception.getErrorMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Validation failed");
        errorResponse.setErrors(ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    return new ErrorMessge(fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.toList()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
