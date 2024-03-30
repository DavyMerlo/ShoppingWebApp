package com.davy.restapi.orderlines.service;

import com.davy.restapi.orderlines.entity.OrderLineEntity;
import com.davy.restapi.orderlines.repository.OrderLineRepository;
import com.davy.restapi.orderlines.request.OrderLineRequest;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderLineServiceImpl extends CrudServiceImpl<OrderLineEntity, OrderLineRequest>
    implements OrderLineService{


    public OrderLineServiceImpl(CrudRepository<OrderLineEntity> repository,
                                ObjectMapper<OrderLineRequest, OrderLineEntity> mapper) {
        super(repository, mapper);
    }
}


//@Service
//@RequiredArgsConstructor
//public class OrderLineServiceImpl implements OrderLineService {
//
//    private final OrderLineRepository orderLineRepository;
//    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;
//    private final OrderLineMapper orderLineMapper;
//    private final OrderMapper orderMapper;
//
//    @Override
//    public OrderLineListResponse findAllOrderLines() {
//        return null;
//    }
//
//    @Override
//    public OrderLineResponse findOrderLineById(Long id) {
//        var response = new OrderLineResponse();
//        if(orderLineRepository.getOrderLineById(id).isEmpty()){
//            ThrowException.objectByIdException(id, "OrderLine");
//        }
//        response.setOrderline(orderLineRepository.getOrderLineById(id)
//                .stream()
//                .map(orderLineMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public OrderResponse findOrderByOrderLineId(Long orderId) {
//        var response = new OrderResponse();
//        if(orderLineRepository.getOrderByOrderLineId(orderId).isEmpty()){
//            ThrowException.objectByIdException(orderId, "Order");
//        }
//        response.setOrder(orderLineRepository.getOrderByOrderLineId(orderId)
//                .stream()
//                .map(orderMapper)
//                .findFirst()
//                .get());
//        return response;
//    }
//
//    @Override
//    public OrderLineListResponse findOrderLinesByOrderId(Long id) {
//        var response = new OrderLineListResponse();
//        if(orderLineRepository.findOrderLinesByOrderId(id).isEmpty()){
//            ThrowException.objectByIdException(id, "OrderLine");
//        }
//        response.setOrderlines(orderLineRepository.getAllOrderlines()
//                .stream()
//                .map(orderLineMapper)
//                .collect(Collectors.toList()));
//        return response;
//    }
//
//    @Override
//    public void saveOrderOrderLines(Long userId, List<OrderLineCreateRequest> request) {
//        var order = orderRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
//        List<OrderLine> orderLines = new ArrayList<>();
//        System.out.println(order.get().getId());
//        for (OrderLineCreateRequest item : request) {
//            ProductEntity productEntity = productRepository.getProductById(item.getProductId()).get();
//            OrderLine orderLine = OrderLine.builder()
//                    .order(order.get())
//                    .product(productEntity)
//                    .quantity(item.getQuantity())
//                    .build();
//            orderLines.add(orderLine);
//        }
//        orderLineRepository.saveAll(orderLines);
//    }
//
//    @Override
//    public OrderLineResponse updateOrderLineById(Long id, OrderLineUpdateRequest request) {
//        return null;
//    }
//}
