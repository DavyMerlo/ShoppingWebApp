package com.davy.restapi.payment.controller;

import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.dto.OrderInvoiceDTO;
import com.davy.restapi.order.dto.OrderPriceDTO;
import com.davy.restapi.order.service.OrderService;
import com.davy.restapi.orderlines.dto.OrderLineInvoiceDTO;
import com.davy.restapi.payment.dto.PaymentDetailsDTO;
import com.davy.restapi.payment.dto.PaymentInvoiceDTO;
import com.davy.restapi.payment.response.PaymentInvoiceResponse;
import com.davy.restapi.payment.service.PaymentService;
import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.user.dto.UserInvoiceDTO;
import com.davy.restapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findPaymentById(@PathVariable(value = "id") final Long id){

        var data = paymentService.findById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping("/{orderId}/invoice")
    public ResponseEntity<?> findInvoiceByOrderId(@PathVariable(value = "orderId") final Long orderId){
        var response = new PaymentInvoiceResponse();
        var orderDetails = (OrderDetailDTO)orderService.findById(orderId);
        var userDetails = userService.findUserById(orderDetails.userId());
        var paymentDetails = (PaymentDetailsDTO)paymentService.findById(orderDetails.id());
        var orderPriceInfo = (OrderPriceDTO)orderService.getTotalPriceByOrderId(orderDetails.id());

        List<OrderLineInvoiceDTO> orderLines = new ArrayList<>();

        for(var item : orderDetails.orderLines()){
            var orderLine = new OrderLineInvoiceDTO(
                    item.getId(),
                    null,
                    item.getQuantity(),
                    item.getTotalPrice()
            );
            orderLines.add(orderLine);
        }

        var user = new UserInvoiceDTO(
                userDetails.getUser().id(),
                userDetails.getUser().firstName(),
                userDetails.getUser().lastName(),
                null
        );

        var order = new OrderInvoiceDTO(
                orderDetails.id(),
                orderDetails.date(),
                orderLines,
                null,
                null,
                null,
                null
        );

        response.setOrder(order);
        response.setUser(user);

        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }
}
