package com.davy.restapi.payment.mapper;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.payment.request.PaymentRequest;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentMapper implements ObjectMapper<PaymentRequest, PaymentEntity> {

    private final CrudRepository<OrderEntity> orderRepository;

    @Override
    public Object mapSourceToDestination(PaymentRequest source, PaymentEntity destination) {
        return null;
    }

    @Override
    public Object mapToDto(PaymentEntity entity) {
        return null;
    }

    @Override
    public Object mapToDetailsDto(PaymentEntity entity) {
        return null;
    }

    @Override
    public PaymentEntity mapToEntity(PaymentRequest request) {
        return PaymentEntity.builder()
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(request.getPaymentStatus())
                .build();
    }

    @Override
    public Object mapToListDto(PaymentEntity entity) {
        return null;
    }
}
