package com.davy.restapi.inventory.response;

import com.davy.restapi.inventory.dto.InventoryItems;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class InventoryListResponse {

    @JsonProperty("inventories")
    public List<InventoryItems> inventories;
    {
        inventories = new ArrayList<>();
    }
}
