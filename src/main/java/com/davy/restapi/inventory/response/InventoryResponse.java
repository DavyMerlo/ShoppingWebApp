package com.davy.restapi.inventory.response;

import com.davy.restapi.inventory.dto.Inventory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryResponse {

    @JsonProperty("inventory")
    public Inventory inventory;
}
