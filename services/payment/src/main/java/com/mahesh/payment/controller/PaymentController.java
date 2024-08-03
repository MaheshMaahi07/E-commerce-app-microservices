package com.mahesh.payment.controller;

import com.mahesh.payment.dto.PaymentRequest;
import com.mahesh.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public ResponseEntity<Integer> createPayment(@RequestBody @Valid
                                                 PaymentRequest request){
        return ResponseEntity.ok(paymentService.createPayment(request));
    }
}
