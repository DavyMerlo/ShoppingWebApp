package com.davy.restapi.inventory.service;


import com.davy.restapi.inventory.entity.InventoryEntity;
import com.davy.restapi.inventory.repository.InventoryRepository;
import com.davy.restapi.inventory.request.InventoryCreateRequest;
import com.davy.restapi.inventory.request.InventoryUpdateRequest;
import com.davy.restapi.inventory.response.InventoryListResponse;
import com.davy.restapi.inventory.response.InventoryResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final Mapper<InventoryEntity,
            com.davy.restapi.inventory.dto.Inventory> inventoryMapper;

    @Override
    public InventoryListResponse findAllInventories() {
        var response = new InventoryListResponse();
        if(inventoryRepository.getAllInventories().isEmpty()){
            ThrowException.objectException("Inventories");
        }
        response.inventories = inventoryRepository.getAllInventories()
                .stream()
                .map(inventoryMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public InventoryResponse findInventoryById(Long id) {
        var response = new InventoryResponse();
        if(inventoryRepository.getInventoryById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Inventory");
        }
        response.inventory = inventoryRepository.getInventoryById(id)
                .stream()
                .map(inventoryMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public InventoryResponse saveInventory(InventoryCreateRequest request) {
        var inventory = InventoryEntity.builder()
                .quantity(request.quantity)
                .build();
        inventoryRepository.saveInventory(inventory);
        return this.findInventoryById(inventory.getId());
    }

    @Override
    public InventoryResponse updateInventoryByID(Long id, InventoryUpdateRequest request) {
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
