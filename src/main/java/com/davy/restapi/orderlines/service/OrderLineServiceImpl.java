//package com.davy.restapi.orderlines.service;
//
//import com.davy.restapi.order.mapper.OrderMapper;
//import com.davy.restapi.order.repository.OrderRepository;
//import com.davy.restapi.order.response.OrderResponse;
//import com.davy.restapi.orderlines.entity.OrderLine;
//import com.davy.restapi.orderlines.mapper.OrderLineMapper;
//import com.davy.restapi.orderlines.repository.OrderLineRepository;
//import com.davy.restapi.orderlines.request.OrderLineCreateRequest;
//import com.davy.restapi.orderlines.request.OrderLineUpdateRequest;
//import com.davy.restapi.orderlines.response.OrderLineListResponse;
//import com.davy.restapi.orderlines.response.OrderLineResponse;
//import com.davy.restapi.product.entity.ProductEntity;
//import com.davy.restapi.product.repository.ProductRepository;
//import com.davy.restapi.shared.exceptions.ThrowException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class OrderLineServiceImpl implements OrderLineService {
//
//    private final OrderLineRepository orderLineRepository;
//    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;
//    private final OrderLineMapper orderLineMapper;
//    private final OrderMapper orderMapper;
//
//    @Override
//    public OrderLineListResponse findAllOrderLines() {
//        return null;
//    }
//
//    @Override
//    public OrderLineResponse findOrderLineById(Long id) {
//        var response = new OrderLineResponse();
//        if(orderLineRepository.getOrderLineById(id).isEmpty()){
//            ThrowException.objectByIdException(id, "OrderLine");
//        }
//        response.setOrderline(orderLineRepository.getOrderLineById(id)
//                .stream()
//                .map(orderLineMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public OrderResponse findOrderByOrderLineId(Long orderId) {
//        var response = new OrderResponse();
//        if(orderLineRepository.getOrderByOrderLineId(orderId).isEmpty()){
//            ThrowException.objectByIdException(orderId, "Order");
//        }
//        response.setOrder(orderLineRepository.getOrderByOrderLineId(orderId)
//                .stream()
//                .map(orderMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public OrderLineListResponse findOrderLinesByOrderId(Long id) {
//        var response = new OrderLineListResponse();
//        if(orderLineRepository.findOrderLinesByOrderId(id).isEmpty()){
//            ThrowException.objectByIdException(id, "OrderLine");
//        }
//        response.setOrderlines(orderLineRepository.getAllOrderlines()
//                .stream()
//                .map(orderLineMapper)
//                .collect(Collectors.toList()));
//        return response;
//    }
//
//    @Override
//    public void saveOrderOrderLines(Long userId, List<OrderLineCreateRequest> request) {
//        var order = orderRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
//        List<OrderLine> orderLines = new ArrayList<>();
//        System.out.println(order.get().getId());
//        for (OrderLineCreateRequest item : request) {
//            ProductEntity productEntity = productRepository.getProductById(item.getProductId()).get();
//            OrderLine orderLine = OrderLine.builder()
//                    .order(order.get())
//                    .product(productEntity)
//                    .quantity(item.getQuantity())
//                    .build();
//            orderLines.add(orderLine);
//        }
//        orderLineRepository.saveAll(orderLines);
//    }
//
//    @Override
//    public OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request) {
//        return null;
//    }
//}
