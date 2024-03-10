package com.davy.restapi.order.response;

import com.davy.restapi.order.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderListResponse {
    @JsonProperty("orders")
    private List<OrderDTO> orders;
    {
        orders = new ArrayList<>();
    }
}
