package com.mahesh.order.service;

import com.mahesh.order.customer.CustomerClient;
import com.mahesh.order.dto.OrderRequest;
import com.mahesh.order.dto.OrderResponse;
import com.mahesh.order.entity.Order;
import com.mahesh.order.exception.BusinessException;
import com.mahesh.order.kafka.OrderConfirmation;
import com.mahesh.order.kafka.OrderProducer;
import com.mahesh.order.mapper.OrderMapper;
import com.mahesh.order.orderline.OrderLineRequest;
import com.mahesh.order.product.ProductClient;
import com.mahesh.order.product.PurchaseRequest;
import com.mahesh.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;


    public Order createOrder(OrderRequest request) {
        //check the customer is exist->openFeign
        var customer=this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException("Cannot create order :: No Customer exist with provided Id"+request.customerId()));

        //purchase the product -->product-ms (Rest-Template)
        var purchasedProducts=this.productClient.purchaseProducts(request.products());

        //persist order
        var order=this.orderRepository.save(mapper.toOrder(request));

        //persist order-line
        for(PurchaseRequest purchaseRequest: request.products()){
        orderLineService.saveOrderLine(
                new OrderLineRequest(
                        null ,
                        order.getId(),
                        purchaseRequest.productId(),
                        purchaseRequest.quantity()
                )
        );
        }

        //start payment process

        //send the order confirmation -->notification-ms (Kafka)
        orderProducer.sendOrderConfirmation(new
                OrderConfirmation(request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
                ));


        return order;
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId){
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new
                        EntityNotFoundException(String.format
                        ("No Order found with the Provided Id :%d",orderId)));
    }
}
