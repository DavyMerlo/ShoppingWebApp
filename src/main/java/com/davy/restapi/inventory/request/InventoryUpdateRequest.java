package com.davy.restapi.inventory.request;

import com.davy.restapi.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InventoryUpdateRequest {

    @JsonProperty("quantity")
    public short quantity;

    @JsonIgnore
    public Product product;
}
