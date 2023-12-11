package com.davy.restapi.payment.controller;

import com.davy.restapi.payment.request.PaymentCreateRequest;
import com.davy.restapi.payment.request.PaymentUpdateRequest;
import com.davy.restapi.payment.service.PaymentService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findPaymentById(@PathVariable(value = "id") final Long id){
        var data = paymentService.findPaymentById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllPayments(){
        var data = paymentService.findAllPayments();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> savePayment(@RequestBody PaymentCreateRequest request){
        var data = paymentService.savePayment(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaymentById(@PathVariable(value = "id") final Long id,
                                             @RequestBody PaymentUpdateRequest request){
        var data = paymentService.updatePaymentById(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
