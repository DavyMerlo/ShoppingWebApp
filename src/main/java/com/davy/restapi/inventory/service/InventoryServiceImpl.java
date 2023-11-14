package com.davy.restapi.inventory.service;

import com.davy.restapi.inventory.entity.Inventory;
import com.davy.restapi.inventory.mapper.InventoryItemsMapper;
import com.davy.restapi.inventory.mapper.InventoryUpdateMapper;
import com.davy.restapi.inventory.repository.InventoryRepository;
import com.davy.restapi.inventory.request.InventoryCreateRequest;
import com.davy.restapi.inventory.request.InventoryUpdateRequest;
import com.davy.restapi.inventory.response.InventoryListResponse;
import com.davy.restapi.inventory.response.InventorySingleResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryItemsMapper inventoryItemsMapper;
    private final InventoryUpdateMapper inventoryUpdateMapper;

    @Override
    public InventoryListResponse findAllInventories() {
        var response = new InventoryListResponse();
        if(inventoryRepository.getAllInventories().isEmpty()){
            ThrowException.objectException("Inventories");
        }
        response.inventories = inventoryRepository.getAllInventories()
                .stream()
                .map(inventoryItemsMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public InventorySingleResponse findInventoryById(Long id) {
        var response = new InventorySingleResponse();
        if(inventoryRepository.getInventoryById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Inventory");
        }
        response.inventory = inventoryRepository.getInventoryById(id)
                .stream()
                .map(inventoryItemsMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public InventorySingleResponse saveInventory(InventoryCreateRequest request) {
        var inventory = Inventory.builder()
                .quantity(request.quantity)
                .build();
        inventoryRepository.saveInventory(inventory);
        return this.findInventoryById(inventory.getId());
    }

    @Override
    public InventorySingleResponse updateInventoryByID(Long id, InventoryUpdateRequest request) {
        var inventory = inventoryRepository.getInventoryById(id);
        if(inventory.isEmpty()){
            ThrowException.objectByIdException(id, "Inventory");
        }
        inventory.get().setQuantity(request.getQuantity());
        inventoryRepository.updateInventory(inventory.get());
        return this.findInventoryById(inventory.get().getId());
    }

    @Override
    public void softDeleteInventoryById(Long id) {
        inventoryRepository.removeInventory(id);
    }
}
