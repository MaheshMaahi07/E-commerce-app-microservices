package com.mahesh.product.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessge {
    private String field;
    private String message;
}
