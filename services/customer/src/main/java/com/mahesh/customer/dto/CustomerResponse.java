package com.mahesh.customer.dto;

import com.mahesh.customer.entity.Address;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
