package com.davy.restapi.inventory.repository;

import com.davy.restapi.inventory.entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface CustomInventoryRepository {
    List<Inventory> getAllInventories();
    Optional<Inventory> getInventoryById(Long id);
    Inventory saveInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    void removeInventory(Long id);
}
