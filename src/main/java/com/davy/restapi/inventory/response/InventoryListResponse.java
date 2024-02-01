package com.davy.restapi.inventory.response;

import com.davy.restapi.inventory.dto.Inventory;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class InventoryListResponse {

    @JsonProperty("inventories")
    public List<Inventory> inventories;
    {
        inventories = new ArrayList<>();
    }
}
