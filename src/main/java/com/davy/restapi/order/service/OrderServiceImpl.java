package com.davy.restapi.order.service;

import com.davy.restapi.order.dto.OrderDTO;
import com.davy.restapi.order.dto.OrderDetailDTO;
import com.davy.restapi.order.dto.OrderPriceDTO;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.order.enums.OrderStatus;
import com.davy.restapi.order.repository.OrderRepository;
import com.davy.restapi.order.request.OrderRequest;
import com.davy.restapi.orderlines.entity.OrderLineEntity;
import com.davy.restapi.orderlines.repository.OrderLineRepository;
import com.davy.restapi.orderlines.request.OrderLineRequest;
import com.davy.restapi.payment.entity.PaymentEntity;
import com.davy.restapi.payment.enums.PaymentMethod;
import com.davy.restapi.payment.enums.PaymentStatus;
import com.davy.restapi.payment.request.PaymentRequest;
import com.davy.restapi.product.enums.Vat;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import com.davy.restapi.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends CrudServiceImpl<OrderEntity, OrderRequest>
    implements OrderService{

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CrudRepository<OrderEntity> orderCrudRepository;
    private final ObjectMapper<OrderRequest, OrderEntity> orderMapper;
    private final ObjectMapper<PaymentRequest, PaymentEntity> paymentMapper;
    private final ObjectMapper<OrderLineRequest, OrderLineEntity> orderLineMapper;

    public OrderServiceImpl(CrudRepository<OrderEntity> repository,
                            ObjectMapper<OrderRequest, OrderEntity> mapper,
                            OrderLineRepository orderLineRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository,
                            CrudRepository<OrderEntity> orderCrudRepository,
                            ObjectMapper<OrderRequest, OrderEntity> orderMapper,
                            ObjectMapper<PaymentRequest, PaymentEntity> paymentMapper,
                            ObjectMapper<OrderLineRequest, OrderLineEntity> orderLineMapper) {
        super(repository, mapper);
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderCrudRepository = orderCrudRepository;
        this.orderMapper = orderMapper;
        this.paymentMapper = paymentMapper;
        this.orderLineMapper = orderLineMapper;
    }

    @Override
    public OrderDetailDTO create(OrderRequest request) {
        var user = userRepository.findById(request.getUserId()).get();
        BigDecimal totalPrice = calculateTotalPrice(request.getOrderLines(), false);
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(totalPrice)
                .paymentStatus(PaymentStatus.SUCCEED)
                .paymentMethod(PaymentMethod.DEBIT_CARD)
                .build();
        PaymentEntity paymentEntity = paymentMapper.mapToEntity(paymentRequest);
        var orderEntity = OrderEntity.builder()
                .user(user)
                .payment(paymentEntity)
                .orderItems(mapOrderLines(request.getOrderLines()))
                .status(OrderStatus.PAID)
                .build();
        orderCrudRepository.save(orderEntity);
        return (OrderDetailDTO) orderMapper.mapToDetailsDto(orderEntity);
    }

    @Override
    public OrderPriceDTO getTotalPriceByOrderId(Long orderId){
        var orderLines = orderLineRepository.findOrderLinesByOrderId(orderId);
        if(orderLines.isEmpty()){
            ThrowException.objectException("Orders");
        }
        var totalPrice = calculateTotalPrice(orderLines, true);
        var totalVat = calculateTotalVatAmount(orderLines);
        return OrderPriceDTO.builder()
                .orderId(orderId)
                .vatValue(totalVat)
                .totalPrice(totalPrice)
                .build();
    }

    @Override
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

    private Map<String, Object> mappedOrderPage(Page orderPage){
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
            spec = spec.and(OrderSpecification.byOrderId(orderId));
        }
        if(userId != null){
            spec = spec.and(OrderSpecification.byUserId(userId));
        }
        return spec;
    }

    private BigDecimal calculateTotalPrice(List<?> orderLines, boolean isEntity){
        var total = BigDecimal.ZERO;
        var totalPrice = BigDecimal.ZERO;
        if (isEntity) {
            for (var orderLine : orderLines) {
                OrderLineEntity entity = (OrderLineEntity) orderLine;
                totalPrice = BigDecimal.valueOf(entity.getQuantity())
                        .multiply(entity.getProduct().getSellingPrice());
                total = total.add(totalPrice);
            }
        } else {
            List<OrderLineRequest> request = (List<OrderLineRequest>) orderLines;
            total = request.stream()
                    .map(orderLineMapper::mapToEntity)
                    .map(orderItemEntity -> BigDecimal.valueOf(orderItemEntity.getQuantity())
                            .multiply(orderItemEntity.getProduct().getSellingPrice()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalVatAmount(List<OrderLineEntity> orderLines) {
        BigDecimal totalVatAmount = BigDecimal.ZERO;
        for (var orderLine : orderLines) {
            BigDecimal totalPrice = BigDecimal.valueOf(orderLine.getQuantity())
                    .multiply(orderLine.getProduct().getSellingPrice());
            BigDecimal vatRate = BigDecimal.valueOf(Vat.STANDARD_RATE.getVatValue())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal vatAmount = totalPrice.divide(BigDecimal.ONE.add(vatRate), 2, RoundingMode.HALF_UP)
                    .multiply(vatRate).setScale(2, RoundingMode.HALF_UP);
            totalVatAmount = totalVatAmount.add(vatAmount);
        }
        return totalVatAmount.setScale(2, RoundingMode.HALF_UP);
    }

    private List<OrderLineEntity> mapOrderLines(List<OrderLineRequest> orderLines) {
        return orderLines.stream()
                .map(orderLineMapper::mapToEntity)
                .collect(Collectors.toList());
    }
}
