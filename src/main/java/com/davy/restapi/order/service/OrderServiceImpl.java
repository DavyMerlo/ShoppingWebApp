package com.davy.restapi.order.service;

import com.davy.restapi.order.dto.OrderDTO;
import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.dto.OrderPriceDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.orderlines.repository.OrderLineRepository;
import com.davy.restapi.payment.enums.PaymentStatus;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends CrudServiceImpl<OrderEntity, OrderRequest>
    implements OrderService{

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper<OrderRequest, OrderEntity> orderMapper;

    public OrderServiceImpl(CrudRepository<OrderEntity> repository,
                            ObjectMapper<OrderRequest, OrderEntity> mapper,
                            OrderLineRepository orderLineRepository,
                            OrderRepository orderRepository, ObjectMapper<OrderRequest,
            OrderEntity> orderMapper) {
        super(repository, mapper);
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderPriceDTO getTotalByOrderId(Long orderId){
        float total = 0;
        var orderLines = orderLineRepository.findOrderLinesByOrderId(orderId);
        if(orderLines.isEmpty()){
            ThrowException.objectException("Orders");
        }
        for (var item: orderLines){
            float totalPrice = item.getQuantity() * item.getProduct().getSellingPrice();
            total += totalPrice;
        }
        return OrderPriceDTO.builder()
                .orderId(orderId)
                .totalPrice(total)
                .build();
    }

    public Map<String, Object> filterOrdersPageable(Long userId,
                                                    Long orderId,
                                                    String search,
                                                    int page,
                                                    int pageSize,
                                                    String sortBy,
                                                    String sortOrder){
        Pageable pageable = makePageable(page, pageSize, sortBy, sortOrder);
        Specification<OrderEntity> spec = specification(userId, orderId, search);
        Page<OrderEntity> orderPage = orderRepository.findAll(spec, pageable);
        return mappedOrderPage(orderPage);
    }

    public Map<String, Object> mappedOrderPage(Page orderPage){
        List<OrderEntity> orderEntities = orderPage.getContent();
        Map<String, Object> mappedOrders = new HashMap<>();

        if(orderEntities.isEmpty()){
            ThrowException.objectException("Orders");
        }

        List<OrderDTO> ordersTest = orderEntities
                .stream()
                .map(entity -> (OrderDTO) orderMapper.mapToDto(entity))
                .toList();

        List<OrderDTO> orderDTOs = new ArrayList<>(ordersTest);

        mappedOrders.put("orders", orderDTOs);
        mappedOrders.put("currentPage", orderPage.getNumber());
        mappedOrders.put("count", orderPage.getTotalElements());
        mappedOrders.put("totalPages", orderPage.getTotalPages());
        mappedOrders.put("pageSize", orderPage.getSize());
        mappedOrders.put("next", orderPage.nextPageable().isPaged());
        mappedOrders.put("previous", orderPage.previousPageable().isPaged());
        return mappedOrders;
    }

    private Pageable makePageable(int page, int pageSize, String sortBy, String sortOrder) {
        if (sortBy != null && sortOrder != null) {
            Sort sort;
            if (sortBy.equals("date")) {
                sort = Sort.by(Sort.Direction.fromString(sortOrder), "createdAt");
            } else {
                sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
            }
            return PageRequest.of(page, pageSize, sort);
        } else {
            return PageRequest.of(page, pageSize);
        }
    }

    private Specification<OrderEntity> specification(Long userId, Long orderId, String search){
        Specification<OrderEntity> spec = Specification.where(null);
        if(search != null){
            spec = spec.and(OrderSpecification.bySearch(search));
        }
        if(orderId != null){
            spec = spec.and(OrderSpecification.byOrder(orderId));
        }
        if(userId != null){
            spec = spec.and(OrderSpecification.byUser(userId));
        }
        return spec;
    }
}
