package com.davy.restapi.order.controller;

import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.service.OrderService;
import com.davy.restapi.orderlines.request.OrderLineCreateRequest;
import com.davy.restapi.orderlines.service.OrderLineService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderLineService orderLineService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable(value = "id") final Long id){
        var data = orderService.findOrderById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping("/{orderId}/orderlines")
    public ResponseEntity<?> findOrderLinesByOrderId(@PathVariable(value = "orderId") final Long id){
        var data = orderLineService.findOrderLinesByOrderId(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllOrders(){
        var data = orderService.findAllOrders();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping("user/{userId}")
    public ResponseEntity<?> saveOrders(@PathVariable(value = "userId") final Long userId,
                                        @RequestBody List<OrderLineCreateRequest> request){
        orderService.saveOrder(userId);
        orderLineService.saveOrderOrderLines(userId, request);
        var data = orderService.findOrderByUserId(userId);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable(value = "id") final Long id,
                                               @RequestBody OrderUpdateRequest request){
        orderService.updateOrderById(id, request);
        var data = orderService.findOrderById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
