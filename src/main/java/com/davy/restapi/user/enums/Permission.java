package com.davy.restapi.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("categories: read, products:read, addresses:read, cards:read"),
    ADMIN_UPDATE("categories: update, products:update, addresses:update, cards:update"),
    ADMIN_CREATE("categories: create, products:create, addresses:create, cards:create"),
    ADMIN_DELETE("categories: delete, products:delete, addresses:delete, cards:delete"),

    MANAGER_READ("categories: read, products:read, addresses:read, users:read, card:read"),
    MANAGER_UPDATE("categories: update, products:update, addresses:update, users:update, cards:update"),
    MANAGER_CREATE("categories: create, products:create, addresses:create, users:create, cards:create"),
    MANAGER_DELETE("categories: delete, products:delete, addresses:delete, users:delete, cards:delete");



    private final String permission;
}
