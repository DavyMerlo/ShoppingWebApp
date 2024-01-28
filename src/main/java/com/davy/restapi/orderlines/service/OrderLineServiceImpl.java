package com.davy.restapi.orderlines.service;

import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.orderlines.entity.OrderLine;
import com.davy.restapi.orderlines.repository.OrderLineRepository;
import com.davy.restapi.orderlines.request.OrderLineCreateRequest;
import com.davy.restapi.orderlines.request.OrderLineUpdateRequest;
import com.davy.restapi.orderlines.response.OrderLineListResponse;
import com.davy.restapi.orderlines.response.OrderLineResponse;
import com.davy.restapi.product.entity.Product;
import com.davy.restapi.product.repository.ProductRepository;
import com.davy.restapi.shared.exceptions.ThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
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
    public void saveOrderOrderLines(Long userId, List<OrderLineCreateRequest> request) {
        var order = orderRepository.getOrderByUserId(userId);
        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineCreateRequest item : request) {
            Product product = productRepository.getProductById(item.getProductId()).get();
            OrderLine orderLine = OrderLine.builder()
                    .order(order.get())
                    .product(product)
                    .quantity(item.getQuantity())
                    .build();
            orderLines.add(orderLine);
        }
        orderLineRepository.saveAll(orderLines);
    }

    @Override
    public OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request) {
        return null;
    }
}
