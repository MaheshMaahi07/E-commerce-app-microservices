package com.mahesh.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {

    //private Integer id; //this is not needed bcz we are directly defining this class in Customer
    private String street;
    private String houseNumber;
    private String landMark;
    private Number pinCode;

}
