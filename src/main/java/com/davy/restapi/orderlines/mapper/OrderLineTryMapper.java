package com.davy.restapi.orderlines.mapper;

import com.davy.restapi.inventory.repository.InventoryRepository;
import com.davy.restapi.orderlines.dto.OrderLine;
import com.davy.restapi.orderlines.dto.OrderLineDetail;
import com.davy.restapi.orderlines.entity.OrderLineEntity;
import com.davy.restapi.orderlines.request.OrderLineRequest;
import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.product.entity.ProductEntity;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderLineTryMapper implements ObjectMapper<OrderLineRequest, OrderLineEntity> {

    private final CrudRepository<ProductEntity> productRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public Object mapSourceToDestination(OrderLineRequest source,
                                         OrderLineEntity destination) {
        return null;
    }

    @Override
    public OrderLine mapToDto(OrderLineEntity entity) {
        return null;
    }

    @Override
    public OrderLineDetail mapToDetailsDto(OrderLineEntity entity) {
        var totalPrice = entity.getQuantity() * entity.getProduct().getSellingPrice();
        return new OrderLineDetail(
                entity.getId(),
                new ProductDTO(
                        entity.getProduct().getId(),
                        entity.getProduct().getName(),
                        entity.getProduct().getSellingPrice()),
                entity.getQuantity(),
                totalPrice
        );
    }

    @Override
    public OrderLineEntity mapToEntity(OrderLineRequest request) {
        var product = productRepository.getById(request.getProductId());
        return OrderLineEntity.builder()
                .product(product.get())
                .quantity(request.getQuantity())
                .build();
    }

    @Override
    public Object mapToListDto(OrderLineEntity entity) {
        return null;
    }
}
