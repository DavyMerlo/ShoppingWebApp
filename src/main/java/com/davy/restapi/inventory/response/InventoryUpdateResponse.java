package com.davy.restapi.inventory.response;

import com.davy.restapi.inventory.dto.InventoryUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryUpdateResponse {

    @JsonProperty("inventory")
    public InventoryUpdate inventory;

}
