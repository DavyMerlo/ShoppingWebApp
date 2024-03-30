package com.davy.restapi.orderlines.service;

import com.davy.restapi.orderlines.entity.OrderLineEntity;
import com.davy.restapi.orderlines.request.OrderLineRequest;
import com.davy.restapi.shared.service.CrudService;

public interface OrderLineService extends CrudService<OrderLineEntity, OrderLineRequest> {

//    OrderLineListResponse findAllOrderLines();
//    OrderLineResponse findOrderLineById(Long id);
//    OrderResponse findOrderByOrderLineId(Long orderId);
//    OrderLineListResponse findOrderLinesByOrderId(Long id);
//    void saveOrderOrderLines(Long userId, List<OrderLineCreateRequest> requests);
//    OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request);
}
