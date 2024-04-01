package com.davy.restapi.order.service;

import com.davy.restapi.order.dto.OrderPriceDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.shared.service.CrudService;

import java.util.Map;

public interface OrderService extends CrudService<OrderEntity, OrderRequest> {

    OrderPriceDTO getTotalByOrderId(Long orderId);
    Map<String, Object> filterOrdersPageable(Long userId,
                                             Long orderId,
                                             String status,
                                             int page,
                                             int pageSize,
                                             String sortBy,
                                             String sortOrder);
//    OrderListResponse findAllOrders();
//    OrderResponse findOrderById(Long id);
//    OrderListResponse findOrdersByUserId(Long userId);
//    OrderResponse findOrderByUserId(Long userId);
//    void saveOrder(Long userId);
//    void updateOrderById(Long id, OrderUpdateRequest request);
}
