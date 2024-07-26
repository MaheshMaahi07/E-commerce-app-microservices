package com.mahesh.customer.helper;

import com.mahesh.customer.dto.CustomerRequest;
import com.mahesh.customer.dto.CustomerResponse;
import com.mahesh.customer.entity.Customer;
import org.springframework.stereotype.Component;

//In Java, the @Component annotation is a stereotype annotation that marks a class as a Spring component.
//It is a generic annotation that can be used to autodetect and autoconfigure beans in the Spring Framework.

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request){
        if(request==null) {
            return null;
        }
          return Customer.builder()
                    .id(request.getId())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .address(request.getAddress())
                    .build();

    }
    public CustomerResponse fromCustomer(Customer request){
        if(request==null) {
            return null;
        }
        return CustomerResponse.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();

    }
}
