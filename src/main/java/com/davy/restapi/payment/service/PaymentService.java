package com.davy.restapi.payment.service;

import com.davy.restapi.payment.request.PaymentCreateRequest;
import com.davy.restapi.payment.request.PaymentUpdateRequest;
import com.davy.restapi.payment.response.PaymentListResponse;
import com.davy.restapi.payment.response.PaymentResponse;

public interface PaymentService {

    PaymentListResponse findAllPayments();
    PaymentResponse findPaymentById(Long id);
    PaymentListResponse savePayment(PaymentCreateRequest request);
    PaymentResponse updatePaymentById(Long id, PaymentUpdateRequest request);
}
