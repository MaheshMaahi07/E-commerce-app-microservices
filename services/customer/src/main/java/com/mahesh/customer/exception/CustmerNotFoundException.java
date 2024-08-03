package com.mahesh.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

//
//In Java, the @EqualsAndHashCode annotation is used to generate implementations of the equals and hashCode
//methods for a class. When you use @EqualsAndHashCode with callSuper = true,
//it includes the superclass's fields in the generated equals and hashCode methods.
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CustmerNotFoundException extends RuntimeException {
     private  String errorMessage;


}
