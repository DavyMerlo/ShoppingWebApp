package com.davy.restapi.order.response;

import com.davy.restapi.order.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    public OrderDetailDTO order;
}
