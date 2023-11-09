package com.davy.restapi.inventory.response;

import com.davy.restapi.inventory.dto.InventoryItems;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InventorySingleResponse {

    @JsonProperty("inventory")
    public InventoryItems inventory;
}
