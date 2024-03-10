package com.davy.restapi.order.service;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends CrudServiceImpl<OrderEntity, OrderRequest>
    implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(CrudRepository<OrderEntity> repository,
                            ObjectMapper<OrderRequest, OrderEntity> mapper,
                            OrderRepository orderRepository) {
        super(repository, mapper);
        this.orderRepository = orderRepository;
    }

//    @Override
//    public OrderListResponse findAllOrders() {
//        OrderListResponse response = new OrderListResponse();
//        if(orderRepository(.isEmpty()){
//            ThrowException.objectException("Orders");
//        }
//        response.setOrders(orderRepository.getAllOrders()
//                .stream()
//                .map(orderMapper)
//                .collect(Collectors.toList()));
//        return response;
//    }
//
//    @Override
//    public OrderResponse findOrderById(Long id) {
//        OrderResponse response = new OrderResponse();
//        if(orderRepository.getOrderById(id).isEmpty()){
//            ThrowException.objectByIdException(id, "Order");
//        }
//        response.setOrder(orderRepository.getOrderById(id)
//                .stream()
//                .map(orderMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public OrderResponse findOrderByUserId(Long userId) {
//        OrderResponse response = new OrderResponse();
//        if(orderRepository.getOrdersByUserId(userId).isEmpty()){
//            ThrowException.objectByIdException(userId, "Order");
//        }
//        response.setOrder(orderRepository.getOrderByUserId(userId)
//                .stream()
//                .map(orderMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public OrderListResponse findOrdersByUserId(Long userId) {
//        OrderListResponse response = new OrderListResponse();
//        if(orderRepository.getOrdersByUserId(userId).isEmpty()){
//            ThrowException.objectByIdException(userId, "Order");
//        }
//        response.setOrders(orderRepository.getAllOrders()
//                .stream()
//                .map(orderMapper)
//                .collect(Collectors.toList()));
//        return response;
//    }
//
//    @Override
//    public void saveOrderByUserId(Long userId) {
//        var user = super.findById(userId);
//        var order = OrderEntity.builder()
//                .user((UserEntity) user)
//                .orderItems(null)
//                .status(OrderStatus.PAID)
//                .payment(null)
//                .build();
//        super.save(order);
    }
//
//    @Override
//    public void updateOrderById(Long id, OrderUpdateRequest request) {
//
//    }
//}
