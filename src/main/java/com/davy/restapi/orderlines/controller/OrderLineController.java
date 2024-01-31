package com.davy.restapi.orderlines.controller;

import com.davy.restapi.orderlines.request.OrderLineUpdateRequest;
import com.davy.restapi.orderlines.service.OrderLineService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/orderlines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderLineById(@PathVariable(value = "id") final Long id){
        var data = orderLineService.findOrderLineById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllOrderLines(){
        var data = orderLineService.findAllOrderLines();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderLineById(@PathVariable(value = "id") final Long id,
                                             @RequestBody OrderLineUpdateRequest request){
        var data = orderLineService.updateOrderLineById(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
