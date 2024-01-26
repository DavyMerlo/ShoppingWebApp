package com.davy.restapi.order.service;


import com.davy.restapi.order.request.OrderCreateRequest;
import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.response.OrderListResponse;
import com.davy.restapi.order.response.OrderItemsResponse;

public interface OrderService {
    OrderListResponse findAllOrders();
    OrderItemsResponse findOrderById(Long id);
    OrderItemsResponse saveOrder(OrderCreateRequest request);
    OrderItemsResponse upOrderById(Long id, OrderUpdateRequest request);
}
