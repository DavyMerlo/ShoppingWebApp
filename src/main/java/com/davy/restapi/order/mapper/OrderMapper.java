package com.davy.restapi.order.mapper;

import com.davy.restapi.order.dto.OrderDTO;
import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.orderlines.dto.OrderLineDetail;
import com.davy.restapi.orderlines.entity.OrderLineEntity;
import com.davy.restapi.orderlines.request.OrderLineRequest;
import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderMapper implements ObjectMapper<OrderRequest, OrderEntity> {

    private final UserRepository userRepository;
    private final ObjectMapper<OrderLineRequest, OrderLineEntity> orderLineMapper;

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
        for(var item: entity.getOrderItems()){
            var totalPrice = item.getQuantity() * item.getProduct().getSellingPrice();
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
                .status(entity.getStatus())
                .date(entity.getCreatedAt())
                .orderLines(orderLines)
                .build();
    }

    @Override
    public OrderEntity mapToEntity(OrderRequest request) {
        var user = userRepository.findById(request.getUserId());
        var orderLines = request.getOrderLines()
                .stream()
                .map(orderLineMapper::mapToEntity)
                .collect(Collectors.toList());

        return OrderEntity.builder()
                .user(user.get())
                .payment(new PaymentEntity())
                .orderItems(orderLines)
                .status(OrderStatus.PAID)
                .build();
    }

    @Override
    public Object mapToListDto(OrderEntity entity) {
        return null;
    }
}
