package com.davy.restapi.order.service;

import com.davy.restapi.order.dto.OrderPriceDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.shared.service.CrudService;

public interface OrderService extends CrudService<OrderEntity, OrderRequest> {

    OrderPriceDTO getTotalByOrderId(Long orderId);
//    OrderListResponse findAllOrders();
//    OrderResponse findOrderById(Long id);
//    OrderListResponse findOrdersByUserId(Long userId);
//    OrderResponse findOrderByUserId(Long userId);
//    void saveOrder(Long userId);
//    void updateOrderById(Long id, OrderUpdateRequest request);
}
