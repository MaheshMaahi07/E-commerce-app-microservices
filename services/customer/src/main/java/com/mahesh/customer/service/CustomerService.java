package com.mahesh.customer.service;

import com.mahesh.customer.dto.CustomerRequest;
import com.mahesh.customer.dto.CustomerResponse;
import com.mahesh.customer.entity.Customer;
import com.mahesh.customer.exception.CustmerNotFoundException;
import com.mahesh.customer.helper.CustomerMapper;
import com.mahesh.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
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
        Optional<Customer> customerPresent = repo.findById(request.getId());
        if (customerPresent.isPresent()) {
            throw new CustmerNotFoundException("User Already Exist");
        }
        return repo.save(mapper.toCustomer(request));
    }

    public List<Customer> getAllCustomers() {

        return repo.findAll();
    }

    public Customer updateCustomer(CustomerRequest request) {
        Optional<Customer> customer = repo.findById(request.getId());
        if (customer.isEmpty()) {
            throw new CustmerNotFoundException("Customer is not find with this id: " + request.getId() + "Please register first");
        }
        Customer customer1 = mapper.toCustomer(request);
        repo.save(customer1);
        return mapper.toCustomer(request);
    }

    public String deleteCustomer(String id) {
        Optional<Customer> customer = repo.findById(id);
        if (customer.isEmpty()) {
            throw new CustmerNotFoundException("Customer is not find with this id: " + id + "Please register first");
        }
        repo.deleteById(id);
        return "Deleted Successfully";

    }

    public CustomerResponse findById(String id) {
        Optional<Customer> customer = repo.findById(id);
        if (customer.isEmpty()) {
            throw new CustmerNotFoundException("Customer is not find with this id: " + id + "Please register first");
        }

        return mapper.fromCustomer(customer.get());
    }
}
