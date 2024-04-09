package com.davy.restapi.payment.service;

import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.payment.dto.PaymentInvoiceDTO;
import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.payment.request.PaymentRequest;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<PaymentEntity, PaymentRequest>
    implements PaymentService {

    private final CrudRepository<OrderEntity> orderRepository;
    private final CrudRepository<PaymentEntity> paymentRepository;

    public PaymentServiceImpl(CrudRepository<PaymentEntity> paymentRepository,
                              CrudRepository<OrderEntity> orderRepository,
                              ObjectMapper<PaymentRequest, PaymentEntity> mapper,
                              CrudRepository<PaymentEntity> paymentRepository1) {
        super(paymentRepository, mapper);
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository1;
    }

    @Override
    public PaymentInvoiceDTO generateInvoiceByOrderId(Long orderId){

        var order = orderRepository.getById(orderId).get();
        var payment = paymentRepository.getById(order.getId());
//        var totalPrice =
//
//        var invoice = PaymentInvoiceDTO.builder()
//                .paymentId(payment.get().getId())
//                .address(null)
//                .paymentDate(payment.get().getCreatedAt())
//                .paymentMethod(payment.get().getPaymentMethod())
//                .orderId(order.getId())
//                .products(null)
//                .vat(order)


        return null;
    }

//    @Override
//    public PaymentListResponse findAllPayments() {
//        var response = new PaymentListResponse();
//        if(paymentRepository.getAllPayments().isEmpty()){
//            ThrowException.objectException("Payments");
//        }
//        response.payments = paymentRepository.getAllPayments()
//                .stream()
//                .map(paymentItemsMapper)
//                .collect(Collectors.toList());
//        return response;
//    }
//
//    @Override
//    public PaymentResponse findPaymentById(Long id) {
//        var response = new PaymentResponse();
//        if(paymentRepository.getPaymentById(id).isEmpty()){
//            ThrowException.objectByIdException(id, "Payment");
//        }
//        response.setPayment(paymentRepository.getPaymentById(id)
//                .stream()
//                .map(paymentItemsMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public PaymentListResponse savePayment(PaymentCreateRequest request) {
//        var payment = PaymentEntity.builder()
//                .amount(request.getAmount())
//                .paymentMethod(request.getPaymentMethod())
//                .paymentStatus(request.getPaymentStatus())
//                .build();
//        paymentRepository.savePayment(payment);
//        return this.findAllPayments();
//    }
//
//    @Override
//    public PaymentResponse updatePaymentById(Long id, PaymentUpdateRequest request) {
//        var payment = paymentRepository.getPaymentById(id);
//        if(payment.isEmpty()){
//            ThrowException.objectByIdException(id, "Payment");
//        }
//        payment.get().setAmount(request.getAmount());
//        payment.get().setPaymentMethod(request.getPaymentMethod());
//        payment.get().setPaymentStatus(request.getPaymentStatus());
//        paymentRepository.updatePayment(payment.get());
//        return this.findPaymentById(payment.get().getId());
//    }
}
