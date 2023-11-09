package com.davy.restapi.orderlines.service;

import com.davy.restapi.orderlines.repository.OrderLineRepository;
import com.davy.restapi.orderlines.request.OrderLineCreateRequest;
import com.davy.restapi.orderlines.request.OrderLineUpdateRequest;
import com.davy.restapi.orderlines.response.OrderLineListResponse;
import com.davy.restapi.orderlines.response.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;

    @Override
    public OrderLineListResponse findAllOrderLines() {
        return null;
    }

    @Override
    public OrderLineResponse findOrderLineById(Long id) {
        return null;
    }

    @Override
    public OrderLineResponse saveOrderOrderLine(OrderLineCreateRequest request) {
        return null;
    }

    @Override
    public OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request) {
        return null;
    }
}
