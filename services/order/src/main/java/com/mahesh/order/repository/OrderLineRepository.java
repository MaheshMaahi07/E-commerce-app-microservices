package com.mahesh.order.repository;

import com.mahesh.order.orderline.OrderLine;
import com.mahesh.order.orderline.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {

    List<OrderLine> findAllByOrderId(Integer orderId);
}
