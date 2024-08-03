package com.mahesh.order.exception.handler;

import com.mahesh.order.exception.BusinessException;
import com.mahesh.product.exception.FourNotFour;
import com.mahesh.product.exception.ProductAlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<String> handleProductException(ProductAlreadyExistException exception){
        return ResponseEntity.status((HttpStatus.ALREADY_REPORTED))
                .body(exception.getErrorMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public  ResponseEntity<String> handle(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
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
