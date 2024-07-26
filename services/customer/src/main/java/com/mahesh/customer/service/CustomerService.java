package com.mahesh.customer.service;

import com.mahesh.customer.dto.CustomerRequest;
import com.mahesh.customer.entity.Customer;
import com.mahesh.customer.helper.CustomerMapper;
import com.mahesh.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository repo;

    @Autowired
    private final CustomerMapper mapper;

    public Customer createCustomer(CustomerRequest request) {
        Optional<Customer> customerPresent=repo.findById(request.getId());
        if(customerPresent.isPresent()){
            throw new RuntimeException("User Already Exist");
        }
    return repo.save(mapper.toCustomer(request));
    }

    public  List<Customer> getAllCustomers() {

        return repo.findAll();
    }
}
