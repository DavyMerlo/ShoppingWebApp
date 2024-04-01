package com.davy.restapi.order.controller;

import com.davy.restapi.order.service.OrderService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/orders")
@RequiredArgsConstructor
public class OrderV2Controller {

    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<?> filterOrders(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "user", required = false) Long userId,
            @RequestParam(name = "orderId", required = false) Long orderId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pagesize", defaultValue = "8") int pageSize,
            @RequestParam(name = "sort", required = false) String sortBy,
            @RequestParam(name = "order", required = false, defaultValue = "asc") String sortOrder) {

        var filteredOrders = orderService.filterOrdersPageable(
                userId,
                orderId,
                search,
                page,
                pageSize,
                sortBy,
                sortOrder);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, filteredOrders);
    }

}
