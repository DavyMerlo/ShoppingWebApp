package com.davy.restapi.inventory.repository;

import com.davy.restapi.inventory.entity.InventoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomInventoryRepositoryImpl implements CustomInventoryRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<InventoryEntity> getAllInventories() {
        Query query = entityManager.createQuery(
                "SELECT I from InventoryEntity I WHERE deletedAt = NULL ORDER BY id");
        return query.getResultList();
    }

    @Override
    public Optional<InventoryEntity> getInventoryById(Long id) {
        return Optional.ofNullable(entityManager.find(InventoryEntity.class, id));
    }

    @Override
    @Transactional
    public InventoryEntity saveInventory(InventoryEntity inventory) {
        entityManager.persist(inventory);
        return inventory;
    }

    @Override
    @Transactional
    public void updateInventory(InventoryEntity inventory) {
        entityManager.merge(inventory);
    }

    @Override
    @Transactional
    public void removeInventory(Long id) {
        InventoryEntity inventory = entityManager.find(InventoryEntity.class, id);
        inventory.setDeletedAt(LocalDateTime.now());
    }
}
