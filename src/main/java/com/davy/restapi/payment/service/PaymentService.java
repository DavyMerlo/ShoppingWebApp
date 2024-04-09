package com.davy.restapi.payment.service;

import com.davy.restapi.payment.dto.PaymentInvoiceDTO;
import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.payment.request.PaymentRequest;
import com.davy.restapi.shared.service.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService extends CrudService<PaymentEntity, PaymentRequest> {

    PaymentInvoiceDTO generateInvoiceByOrderId(Long orderId);
}
