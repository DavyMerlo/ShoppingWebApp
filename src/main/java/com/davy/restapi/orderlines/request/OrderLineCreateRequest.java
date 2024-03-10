package com.davy.restapi.orderlines.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderLineCreateRequest {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("quantity")
    private short quantity;

}
