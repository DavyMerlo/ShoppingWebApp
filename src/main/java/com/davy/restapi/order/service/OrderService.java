package com.davy.restapi.order.service;

import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.response.OrderListResponse;
import com.davy.restapi.order.response.OrderResponse;

public interface OrderService {
    OrderListResponse findAllOrders();
    OrderResponse findOrderById(Long id);
    OrderResponse findOrderByUserId(Long userId);
    void saveOrder(Long userId);
    void updateOrderById(Long id, OrderUpdateRequest request);
}
