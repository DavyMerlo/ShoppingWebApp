package com.davy.restapi.payment.response;

import com.davy.restapi.order.dto.OrderInvoiceDTO;
import com.davy.restapi.payment.dto.PaymentInvoiceDTO;
import com.davy.restapi.user.dto.UserInvoiceDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInvoiceResponse {

    @JsonProperty("user")
    UserInvoiceDTO user;

    @JsonProperty("order")
    OrderInvoiceDTO order;

    @JsonProperty("payment")
    PaymentInvoiceDTO payment;
}
