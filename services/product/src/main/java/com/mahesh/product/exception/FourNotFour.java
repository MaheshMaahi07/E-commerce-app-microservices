package com.mahesh.product.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class FourNotFour extends RuntimeException {
   private final String errorMessage;
}
