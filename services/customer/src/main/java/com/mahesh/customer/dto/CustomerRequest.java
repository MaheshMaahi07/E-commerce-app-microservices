package com.mahesh.customer.dto;

import com.mahesh.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Data
public class CustomerRequest {

    String id;

    @NotNull(message = "Customer firstname is required")
    String firstName;

    @NotNull(message = "Customer firstname is required")
    String lastName;

    @NotNull(message = "Customer Email is required")
    @Email(message = "Customer Email is not a valid email address")
    String email;

    Address address;

}
