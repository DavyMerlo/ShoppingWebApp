package com.davy.restapi.inventory.mapper;

import com.davy.restapi.inventory.dto.Inventory;
import com.davy.restapi.shared.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper implements Mapper<com.davy.restapi.inventory.entity.Inventory, Inventory> {

    @Override
    public Inventory apply(com.davy.restapi.inventory.entity.Inventory source) {
        return new Inventory(
                source.getId(),
                source.getQuantity()
        );
    }
}