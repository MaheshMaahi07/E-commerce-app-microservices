package com.mahesh.order.service;

import com.mahesh.order.mapper.OrderLineMapper;
import com.mahesh.order.orderline.OrderLine;
import com.mahesh.order.orderline.OrderLineRequest;
import com.mahesh.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order=mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();

    }
}
