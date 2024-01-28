package com.davy.restapi.orderlines.response;

import com.davy.restapi.order.dto.Order;
import lombok.Data;

@Data
public class OrderLineResponse {
    Order order;
}
