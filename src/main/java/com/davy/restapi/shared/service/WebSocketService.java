package com.davy.restapi.shared.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(final SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(final Object topicSuffix){
        messagingTemplate.convertAndSend("/topic/" + topicSuffix, "Default message from our WS service");
    }
}
