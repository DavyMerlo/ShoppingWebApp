package com.davy.restapi.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("addresses:read, cards:read"),
    ADMIN_UPDATE("addresses:update, cards:update"),
    ADMIN_CREATE("addresses:create, cards:create"),
    ADMIN_DELETE("addresses:delete, cards:delete"),

    MANAGER_READ("addresses:read, users:read, card:read"),
    MANAGER_UPDATE("addresses:update, users:update, cards:update"),
    MANAGER_CREATE("addresses:create, users:create, cards:create"),
    MANAGER_DELETE("addresses:delete, users:delete, cards:delete");



    private final String permission;
}
