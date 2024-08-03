package com.mahesh.order.service;

import com.mahesh.order.mapper.OrderLineMapper;
import com.mahesh.order.orderline.OrderLine;
import com.mahesh.order.orderline.OrderLineRequest;
import com.mahesh.order.orderline.OrderLineResponse;
import com.mahesh.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order=mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();

    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
