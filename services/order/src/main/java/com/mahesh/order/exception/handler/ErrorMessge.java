package com.mahesh.order.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessge {
    private String field;
    private String message;
}
