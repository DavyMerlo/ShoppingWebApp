package com.davy.restapi.order.service;

import com.davy.restapi.order.dto.OrderDTO;
import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.dto.OrderPriceDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.orderlines.repository.OrderLineRepository;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends CrudServiceImpl<OrderEntity, OrderRequest>
    implements OrderService{

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CrudRepository<OrderEntity> repository,
                            ObjectMapper<OrderRequest, OrderEntity> mapper,
                            OrderLineRepository orderLineRepository, OrderRepository orderRepository) {
        super(repository, mapper);
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
    }

    public OrderPriceDTO getTotalByOrderId(Long orderId){
        float total = 0;
        var orderLines = orderLineRepository.findOrderLinesByOrderId(orderId);
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
                                                    int page,
                                                    int pageSize,
                                                    String sortBy,
                                                    String sortOrder){
        Pageable pageable = makePageable(page, pageSize, sortBy, sortOrder);
        Specification<OrderEntity> spec = specification(userId);
        Page<OrderEntity> orderPage = orderRepository.findAll(spec, pageable);
        return null;
    }

    public Map<String, Object> mappedOrderPage(Page orderPage){
        List<OrderEntity> orderEntities = orderPage.getContent();
        Map<String, Object> mappedOrders = new HashMap<>();
        List<OrderDTO> orderDetails = new ArrayList<>();
        return null;
    }

    private Pageable makePageable(int page, int pageSize, String sortBy, String sortOrder) {
        if (sortBy != null && sortOrder != null) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
            return PageRequest.of(page, pageSize, sort);
        } else {
            return PageRequest.of(page, pageSize);
        }
    }


    private Specification<OrderEntity> specification(Long userId){
        Specification<OrderEntity> spec = Specification.where(null);

        if(userId != null){
            spec = spec.and(OrderSpecification.byUser(userId));
        }
        return spec;
    }

//
//    @Override
//    public OrderResponse findOrderByUserId(Long userId) {
//        OrderResponse response = new OrderResponse();
//        if(orderRepository.getOrdersByUserId(userId).isEmpty()){
//            ThrowException.objectByIdException(userId, "Order");
//        }
//        response.setOrder(orderRepository.getOrderByUserId(userId)
//                .stream()
//                .map(orderMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public OrderListResponse findOrdersByUserId(Long userId) {
//        OrderListResponse response = new OrderListResponse();
//        if(orderRepository.getOrdersByUserId(userId).isEmpty()){
//            ThrowException.objectByIdException(userId, "Order");
//        }
//        response.setOrders(orderRepository.getAllOrders()
//                .stream()
//                .map(orderMapper)
//                .collect(Collectors.toList()));
//        return response;
//    }
//
//    @Override
//    public void saveOrderByUserId(Long userId) {
//        var user = super.findById(userId);
//        var order = OrderEntity.builder()
//                .user((UserEntity) user)
//                .orderItems(null)
//                .status(OrderStatus.PAID)
//                .payment(null)
//                .build();
//        super.save(order);
    }
//
//    @Override
//    public void updateOrderById(Long id, OrderUpdateRequest request) {
//
//    }
//}
