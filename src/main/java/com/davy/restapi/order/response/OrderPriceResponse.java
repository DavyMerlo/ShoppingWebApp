package com.davy.restapi.order.response;

import com.davy.restapi.order.dto.OrderPriceDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPriceResponse {

    @JsonProperty("order")
    public OrderPriceDTO orderPrice;

}
