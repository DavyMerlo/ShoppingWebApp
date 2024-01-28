package com.davy.restapi.order.service;

import com.davy.restapi.order.entity.Order;
import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.order.mapper.OrderMapper;
import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.response.OrderListResponse;
import com.davy.restapi.order.response.OrderResponse;
import com.davy.restapi.orderlines.service.OrderLineService;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderListResponse findAllOrders() {
        OrderListResponse response = new OrderListResponse();
        if(orderRepository.getAllOrders().isEmpty()){
            ThrowException.objectException("Orders");
        }
        response.setOrders(orderRepository.getAllOrders()
                .stream()
                .map(orderMapper)
                .collect(Collectors.toList()));
        return response;
    }

    @Override
    public OrderResponse findOrderById(Long id) {
        OrderResponse response = new OrderResponse();
        if(orderRepository.getOrderById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Order");
        }
        response.setOrder(orderRepository.getOrderById(id)
                .stream()
                .map(orderMapper)
                .findFirst()
                .get());
        return response;
    }

    @Override
    public OrderResponse findOrderByUserId(Long userId) {
        OrderResponse response = new OrderResponse();
        if(orderRepository.getOrderByUserId(userId).isEmpty()){
            ThrowException.objectByIdException(userId, "Order");
        }
        response.setOrder(orderRepository.getOrderByUserId(userId)
                .stream()
                .map(orderMapper)
                .findFirst()
                .get());
        return response;
    }

    @Override
    public void saveOrder(Long userId) {
        var user = userRepository.findById(userId);
        var order = com.davy.restapi.order.entity.Order.builder()
                .user(user.get())
                .orderItems(null)
                .status(OrderStatus.PAID)
                .payment(null)
                .build();
        orderRepository.saveOrder(order);
    }

    @Override
    public void updateOrderById(Long id, OrderUpdateRequest request) {

    }
}
