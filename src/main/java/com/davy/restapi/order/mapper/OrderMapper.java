package com.davy.restapi.order.mapper;

import com.davy.restapi.order.dto.OrderDTO;
import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.orderlines.dto.OrderLineDetail;
import com.davy.restapi.orderlines.entity.OrderLineEntity;
import com.davy.restapi.orderlines.request.OrderLineRequest;
import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.payment.request.PaymentRequest;
import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderMapper implements ObjectMapper<OrderRequest, OrderEntity> {

    private final UserRepository userRepository;
    private final ObjectMapper<OrderLineRequest, OrderLineEntity> orderLineMapper;
    private final ObjectMapper<PaymentRequest, PaymentEntity> paymentMapper;

    @Override
    public OrderEntity mapSourceToDestination(OrderRequest source,
                                              OrderEntity destination) {
        return null;
    }

    @Override
    public OrderDTO mapToDto(OrderEntity entity) {
        return new OrderDTO(
                entity.getId(),
                entity.user.getId(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    @Override
    public OrderDetailDTO mapToDetailsDto(OrderEntity entity) {
        List<OrderLineDetail> orderLines = new ArrayList<>();

        for (var item : entity.getOrderItems()) {
            BigDecimal totalPrice = BigDecimal.valueOf(item.getQuantity())
                    .multiply(item.getProduct().getSellingPrice());
            orderLines.add(new OrderLineDetail(
                    item.getId(),
                    new ProductDTO(
                            item.getProduct().getId(),
                            item.getProduct().getName(),
                            item.getProduct().getSellingPrice()),
                    item.getQuantity(),
                    totalPrice));
        }
        return OrderDetailDTO.builder()
                .id(entity.getId())
                .userId(entity.user.getId())
                .status(entity.getStatus())
                .date(entity.getCreatedAt().withNano(0))
                .orderLines(orderLines)
                .build();
    }

    @Override
    public OrderEntity mapToEntity(OrderRequest request) {
        return null;
    }

    @Override
    public Object mapToListDto(OrderEntity entity) {
        return null;
    }
}
