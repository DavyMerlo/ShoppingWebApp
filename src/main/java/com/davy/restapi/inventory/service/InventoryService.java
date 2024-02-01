package com.davy.restapi.inventory.service;

import com.davy.restapi.inventory.request.InventoryCreateRequest;
import com.davy.restapi.inventory.request.InventoryUpdateRequest;
import com.davy.restapi.inventory.response.InventoryListResponse;
import com.davy.restapi.inventory.response.InventoryResponse;

public interface InventoryService {

    InventoryListResponse findAllInventories();

    InventoryResponse findInventoryById(Long id);

    InventoryResponse saveInventory(InventoryCreateRequest request);

    InventoryResponse updateInventoryByID(Long id, InventoryUpdateRequest request);

    void softDeleteInventoryById(Long id);
}
