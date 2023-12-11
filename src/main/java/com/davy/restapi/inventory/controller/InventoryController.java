package com.davy.restapi.inventory.controller;

import com.davy.restapi.inventory.request.InventoryCreateRequest;
import com.davy.restapi.inventory.request.InventoryUpdateRequest;
import com.davy.restapi.inventory.service.InventoryService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?> findAllInventories(){
        var data = inventoryService.findAllInventories();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findInventoryById(@PathVariable(value = "id") final Long id){
        var data = inventoryService.findInventoryById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventoryById(@PathVariable(value = "id") final Long id,
                                                @RequestBody InventoryUpdateRequest request){
        var data = inventoryService.updateInventoryByID(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventoryById(@PathVariable(value = "id") final Long id){
        inventoryService.softDeleteInventoryById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK,
                "Deleted inventory with id: " + id);
    }

    @PostMapping
    public ResponseEntity<?> saveInventory(@RequestBody InventoryCreateRequest request){
        var data = inventoryService.saveInventory(request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
