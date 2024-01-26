package com.davy.restapi.orderlines.service;

import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.orderlines.entity.OrderLine;
import com.davy.restapi.orderlines.repository.OrderLineRepository;
import com.davy.restapi.orderlines.request.OrderLineCreateRequest;
import com.davy.restapi.orderlines.request.OrderLineUpdateRequest;
import com.davy.restapi.orderlines.response.OrderLineListResponse;
import com.davy.restapi.orderlines.response.OrderLineResponse;
import com.davy.restapi.product.repository.ProductRepository;
import com.davy.restapi.shared.exceptions.ThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderLineListResponse findAllOrderLines() {
        return null;
    }

    @Override
    public OrderLineResponse findOrderLineById(Long id) {
        if(orderLineRepository.getOrderLineById(id).isEmpty()){
            ThrowException.objectByIdException(id, "OrderLine");
        }
        return null;
    }

    @Override
    public OrderLineResponse saveOrderOrderLine(List<OrderLineCreateRequest> requestedOrderlines) {
        for(var requestedOrderLine: requestedOrderlines){
            var orderLine = OrderLine.builder()
                    .product(productRepository.getProductById(requestedOrderLine.getProductId()).get())
                    .quantity(requestedOrderLine.getQuantity())
                    .build();
            orderLineRepository.saveOrderLine(orderLine);
        }
        return null;
    }

    @Override
    public OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request) {
        return null;
    }
}
