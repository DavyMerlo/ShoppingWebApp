package com.davy.restapi.inventory.repository;

import com.davy.restapi.inventory.entity.Inventory;
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
    public List<Inventory> getAllInventories() {
        Query query = entityManager.createQuery(
                "SELECT I from Inventory I WHERE deletedAt = NULL ORDER BY id");
        return query.getResultList();
    }

    @Override
    public Optional<Inventory> getInventoryById(Long id) {
        return Optional.ofNullable(entityManager.find(Inventory.class, id));
    }

    @Override
    @Transactional
    public Inventory saveInventory(Inventory inventory) {
        entityManager.persist(inventory);
        return inventory;
    }

    @Override
    @Transactional
    public void updateInventory(Inventory inventory) {
        entityManager.merge(inventory);
    }

    @Override
    @Transactional
    public void removeInventory(Long id) {
        Inventory inventory = entityManager.find(Inventory.class, id);
        inventory.setDeletedAt(LocalDateTime.now());
    }
}
