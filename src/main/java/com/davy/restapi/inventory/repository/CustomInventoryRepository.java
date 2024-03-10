package com.davy.restapi.inventory.repository;

import com.davy.restapi.inventory.entity.InventoryEntity;

import java.util.List;
import java.util.Optional;

public interface CustomInventoryRepository {
    List<InventoryEntity> getAllInventories();
    Optional<InventoryEntity> getInventoryById(Long id);
    InventoryEntity saveInventory(InventoryEntity inventory);
    void updateInventory(InventoryEntity inventory);
    void removeInventory(Long id);
}
