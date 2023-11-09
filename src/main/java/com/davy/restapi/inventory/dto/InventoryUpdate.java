package com.davy.restapi.inventory.dto;

import lombok.extern.java.Log;

public record InventoryUpdate(Long id,
                              short quantity) {
}
