package com.davy.restapi.order.service;

import com.davy.restapi.category.response.CategoryResponse;
import com.davy.restapi.order.dto.OrderItems;
import com.davy.restapi.order.entity.Order;
import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.order.mapper.OrderItemsMapper;
import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.order.request.OrderCreateRequest;
import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.response.OrderListResponse;
import com.davy.restapi.order.response.OrderItemsResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemsMapper orderItemsMapper;

    @Override
    public OrderListResponse findAllOrders() {
        return null;
    }

    @Override
    public OrderItemsResponse findOrderById(Long id) {
        if(orderRepository.getOrderById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Order");
        }
        var orderItems = mappedOrderItems(id);
        return OrderItemsResponse.builder()
                .orderItems(orderItems)
                .build();
    }

    @Override
    public OrderItemsResponse saveOrder(OrderCreateRequest order) {
        var user = userRepository.findById(order.getUserId()).get();
        var createdOrder = Order.builder()
                .user(user)
                .status(OrderStatus.PAID)
                .build();
        var orderItems = orderRepository.saveOrder(createdOrder)
                .map(orderItemsMapper)
                .get();
        return OrderItemsResponse.builder()
                .orderItems(orderItems)
                .build();
    }

    @Override
    public OrderItemsResponse upOrderById(Long id, OrderUpdateRequest request) {
        return null;
    }


    private OrderItems mappedOrderItems(Long orderId){
        return  orderRepository.getOrderById(orderId)
                .stream()
                .map(orderItemsMapper)
                .findFirst()
                .get();
    }
}
