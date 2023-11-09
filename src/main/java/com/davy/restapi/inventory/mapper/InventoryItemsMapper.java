package com.davy.restapi.inventory.mapper;

import com.davy.restapi.inventory.dto.InventoryItems;
import com.davy.restapi.inventory.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class InventoryItemsMapper implements Function<Inventory, InventoryItems> {

    @Override
    public InventoryItems apply(Inventory inventory) {
        return new InventoryItems(
                inventory.getId(),
                inventory.getQuantity()
        );
    }
}
