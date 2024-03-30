package com.davy.restapi.order.request;

import com.davy.restapi.orderlines.request.OrderLineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderRequest {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("paymentId")
    private Long paymentId;

    @JsonProperty("orderlines")
    private List<OrderLineRequest> orderLines;
}
