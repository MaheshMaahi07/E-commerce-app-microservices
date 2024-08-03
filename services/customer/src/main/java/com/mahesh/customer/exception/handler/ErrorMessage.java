package com.mahesh.customer.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ErrorMessage {
    private String field;
    private String message;

    // getters and setters
}
