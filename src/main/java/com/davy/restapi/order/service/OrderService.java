package com.davy.restapi.order.service;


import com.davy.restapi.order.request.OrderCreateRequest;
import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.response.OrderListResponse;
import com.davy.restapi.order.response.OrderResponse;

public interface OrderService {
    OrderListResponse findAllOrders();
    OrderResponse findOrderById(Long id);
    OrderResponse saveOrder(OrderCreateRequest request);
    OrderResponse upOrderById(Long id, OrderUpdateRequest request);
}
