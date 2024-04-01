package com.davy.restapi.order.controller;

import com.davy.restapi.order.dto.OrderDTO;
import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.order.response.OrderListResponse;
import com.davy.restapi.order.response.OrderPriceResponse;
import com.davy.restapi.order.response.OrderResponse;
import com.davy.restapi.order.service.OrderService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderV1Controller {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable(value = "id") final Long id){
        var response = new OrderResponse();
        var order = orderService.findById(id);
        response.setOrder((OrderDetailDTO) order);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<?> findAllOrders(){
        var response = new OrderListResponse();
        var orders = orderService.findAll();
        response.setOrders((List<OrderDTO>) orders);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @PostMapping()
    public ResponseEntity<?> saveOrderByUserId(@RequestBody OrderRequest request){
        var response = new OrderResponse();
        var order = orderService.save(request);
        response.setOrder((OrderDetailDTO) order);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, response);
    }

    @GetMapping("/{orderId}/total-price")
    public ResponseEntity<?> findTotalPriceByOrderId(@PathVariable(value = "orderId") final Long orderId){
        var response = new OrderPriceResponse();
        var order = orderService.getTotalByOrderId(orderId);
        response.setOrderPrice(order);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

//    @GetMapping
//    public ResponseEntity<?> findOrdersByUserId(@RequestParam(value = "userId") final Long userId) {
//
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateOrderById(@PathVariable(value = "id") final Long id,
//                                               @RequestBody OrderUpdateRequest request){
//        orderService.updateOrderById(id, request);
//        var data = orderService.findOrderById(id);
//        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
//    }
}
