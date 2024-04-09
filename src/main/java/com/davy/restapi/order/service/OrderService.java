package com.davy.restapi.order.service;

import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.dto.OrderPriceDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.shared.service.CrudService;

import java.util.Map;

public interface OrderService extends CrudService<OrderEntity, OrderRequest> {

    OrderDetailDTO create(OrderRequest request);
    OrderPriceDTO getTotalPriceByOrderId(Long orderId);
    Map<String, Object> filterOrdersPageable(Long userId,
                                             Long orderId,
                                             String status,
                                             int page,
                                             int pageSize,
                                             String sortBy,
                                             String sortOrder);

}
