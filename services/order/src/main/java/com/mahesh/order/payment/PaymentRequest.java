package com.mahesh.order.payment;



import com.mahesh.order.customer.CustomerResponse;
import com.mahesh.order.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}