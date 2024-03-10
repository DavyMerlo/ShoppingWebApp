package com.davy.restapi.inventory.repository;

import com.davy.restapi.inventory.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long>,
        CustomInventoryRepository {
}
