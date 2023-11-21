package com.davy.restapi.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("products:read, addresses:read, cards:read"),
    ADMIN_UPDATE("products:update, addresses:update, cards:update"),
    ADMIN_CREATE("products:create, addresses:create, cards:create"),
    ADMIN_DELETE("products:delete, addresses:delete, cards:delete"),

    MANAGER_READ("products:read, addresses:read, users:read, card:read"),
    MANAGER_UPDATE("products:update, addresses:update, users:update, cards:update"),
    MANAGER_CREATE("products:create, addresses:create, users:create, cards:create"),
    MANAGER_DELETE("products:delete, addresses:delete, users:delete, cards:delete");



    private final String permission;
}
