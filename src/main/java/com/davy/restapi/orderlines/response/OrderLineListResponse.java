package com.davy.restapi.orderlines.response;

import com.davy.restapi.orderlines.dto.OrderLine;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderLineListResponse {

    @JsonProperty("orderlines")
    private List<OrderLine> orderlines;
    {
        orderlines = new ArrayList<>();
    }
}
