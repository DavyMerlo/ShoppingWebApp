package com.davy.restapi.inventory.repository;

import com.davy.restapi.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long>,
        CustomInventoryRepository {
}
