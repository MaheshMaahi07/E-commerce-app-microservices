package com.mahesh.order.kafka;

import com.mahesh.order.customer.CustomerResponse;
import com.mahesh.order.enums.PaymentMethod;
import com.mahesh.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}