package com.davy.restapi.shared.config;

import lombok.Data;

@Data
public class WebSocketMessage {

    private String type;
    private Object payload;

    public WebSocketMessage(String type, Object payload) {
        this.type = type;
        this.payload = payload;
    }
}
