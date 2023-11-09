package com.davy.restapi.order.service;

import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.order.request.OrderCreateRequest;
import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.response.OrderListResponse;
import com.davy.restapi.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderListResponse findAllOrders() {
        return null;
    }

    @Override
    public OrderResponse findOrderById(Long id) {
        return null;
    }

    @Override
    public OrderResponse saveOrder(OrderCreateRequest request) {

        //work in progress
        return null;
    }

    @Override
    public OrderResponse upOrderById(Long id, OrderUpdateRequest request) {
        return null;
    }
}
