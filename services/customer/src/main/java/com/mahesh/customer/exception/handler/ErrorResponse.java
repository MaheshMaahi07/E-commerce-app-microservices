package com.mahesh.customer.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private List<ErrorMessage> errors;
}
