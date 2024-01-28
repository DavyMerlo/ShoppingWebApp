package com.davy.restapi.orderlines.service;

import com.davy.restapi.orderlines.request.OrderLineCreateRequest;
import com.davy.restapi.orderlines.request.OrderLineUpdateRequest;
import com.davy.restapi.orderlines.response.OrderLineListResponse;
import com.davy.restapi.orderlines.response.OrderLineResponse;

import java.util.List;

public interface OrderLineService {

    OrderLineListResponse findAllOrderLines();
    OrderLineResponse findOrderLineById(Long id);
    void saveOrderOrderLines(Long userId, List<OrderLineCreateRequest> requests);
    OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request);
}
