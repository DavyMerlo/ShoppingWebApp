package com.davy.restapi.orderlines.service;

import com.davy.restapi.orderlines.request.OrderLineCreateRequest;
import com.davy.restapi.orderlines.request.OrderLineUpdateRequest;
import com.davy.restapi.orderlines.response.OrderLineListResponse;
import com.davy.restapi.orderlines.response.OrderLineResponse;

public interface OrderLineService {

    OrderLineListResponse findAllOrderLines();
    OrderLineResponse findOrderLineById(Long id);
    OrderLineResponse saveOrderOrderLine(OrderLineCreateRequest request);
    OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request);
}
