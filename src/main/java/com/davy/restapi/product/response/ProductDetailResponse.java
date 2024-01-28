package com.davy.restapi.product.response;

import com.davy.restapi.product.dto.ProductDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {

    @JsonProperty("product")
    public ProductDetails product;
}
