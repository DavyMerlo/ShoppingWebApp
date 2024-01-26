package com.davy.restapi.order.controller;

import com.davy.restapi.order.request.OrderCreateRequest;
import com.davy.restapi.order.request.OrderUpdateRequest;
import com.davy.restapi.order.service.OrderService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable(value = "id") final Long id){
        var data = orderService.findOrderById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllOrders(){
        var data = orderService.findAllOrders();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveOrders(@RequestBody OrderCreateRequest request){
        var data = orderService.saveOrder(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderById(@PathVariable(value = "id") final Long id,
                                               @RequestBody OrderUpdateRequest request){
        var data = orderService.upOrderById(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
