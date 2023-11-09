package com.davy.restapi.inventory.mapper;

import com.davy.restapi.inventory.dto.InventoryUpdate;
import com.davy.restapi.inventory.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class InventoryUpdateMapper implements Function<Inventory, InventoryUpdate> {

    @Override
    public InventoryUpdate apply(Inventory inventory) {
        return new InventoryUpdate(
                inventory.getId(),
                inventory.getQuantity()
        );
    }
}
