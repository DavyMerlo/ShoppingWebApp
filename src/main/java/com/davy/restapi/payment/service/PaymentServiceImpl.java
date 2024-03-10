package com.davy.restapi.payment.service;

import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.payment.mapper.PaymentItemsMapper;
import com.davy.restapi.payment.repository.PaymentRepository;
import com.davy.restapi.payment.request.PaymentCreateRequest;
import com.davy.restapi.payment.request.PaymentUpdateRequest;
import com.davy.restapi.payment.response.PaymentListResponse;
import com.davy.restapi.payment.response.PaymentResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentItemsMapper paymentItemsMapper;

    @Override
    public PaymentListResponse findAllPayments() {
        var response = new PaymentListResponse();
        if(paymentRepository.getAllPayments().isEmpty()){
            ThrowException.objectException("Payments");
        }
        response.payments = paymentRepository.getAllPayments()
                .stream()
                .map(paymentItemsMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public PaymentResponse findPaymentById(Long id) {
        var response = new PaymentResponse();
        if(paymentRepository.getPaymentById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Payment");
        }
        response.setPayment(paymentRepository.getPaymentById(id)
                .stream()
                .map(paymentItemsMapper)
                .findFirst()
                .get());
        return response;
    }

    @Override
    public PaymentListResponse savePayment(PaymentCreateRequest request) {
        var payment = PaymentEntity.builder()
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(request.getPaymentStatus())
                .build();
        paymentRepository.savePayment(payment);
        return this.findAllPayments();
    }

    @Override
    public PaymentResponse updatePaymentById(Long id, PaymentUpdateRequest request) {
        var payment = paymentRepository.getPaymentById(id);
        if(payment.isEmpty()){
            ThrowException.objectByIdException(id, "Payment");
        }
        payment.get().setAmount(request.getAmount());
        payment.get().setPaymentMethod(request.getPaymentMethod());
        payment.get().setPaymentStatus(request.getPaymentStatus());
        paymentRepository.updatePayment(payment.get());
        return this.findPaymentById(payment.get().getId());
    }
}
