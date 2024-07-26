package com.mahesh.customer.controller;

import com.mahesh.customer.dto.CustomerRequest;
import com.mahesh.customer.entity.Customer;
import com.mahesh.customer.repository.CustomerRepository;
import com.mahesh.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService service;
    //When a method parameter is annotated with @Valid, Spring will validate the object using a Validator
    // implementation, such as the Bean Validation API (JSR-303).
    // If the object is invalid, Spring will throw a MethodArgumentNotValidException.
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequest request){

        return ResponseEntity.ok(service.createCustomer(request));

    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomer(@RequestBody @Valid CustomerRequest request){

        return ResponseEntity.ok(service.getAllCustomers());

    }


}
