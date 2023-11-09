package com.davy.restapi.inventory.service;

import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.inventory.request.InventoryCreateRequest;
import com.davy.restapi.inventory.request.InventoryUpdateRequest;
import com.davy.restapi.inventory.response.InventoryListResponse;
import com.davy.restapi.inventory.response.InventorySingleResponse;
import com.davy.restapi.inventory.response.InventoryUpdateResponse;
import com.davy.restapi.product.request.ProductRequest;

public interface InventoryService {

    InventoryListResponse findAllInventories();

    InventorySingleResponse findInventoryById(Long id);

    InventorySingleResponse saveInventory(InventoryCreateRequest request);

    InventorySingleResponse updateInventoryByID(Long id, InventoryUpdateRequest request);

    void softDeleteInventoryById(Long id);
}
