package com.davy.restapi.orderlines.response;

import com.davy.restapi.orderlines.dto.OrderLine;
import lombok.Data;

@Data
public class OrderLineResponse {
    OrderLine orderline;
}
