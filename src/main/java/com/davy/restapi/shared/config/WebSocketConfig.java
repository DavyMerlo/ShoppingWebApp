package com.davy.restapi.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/wss").setAllowedOrigins("**");
//        registry.addEndpoint("/wss").setAllowedOrigins("http://localhost:3000").withSockJS();
//        registry.addEndpoint("/wss")
//                .setAllowedOrigins("http://localhost:3000");
//
//        registry.addEndpoint("/api/v1/auth").setAllowedOrigins("**");
//        registry.addEndpoint("/api/v1/auth").setAllowedOrigins("http://localhost:3000").withSockJS();
//        registry.addEndpoint("/api/v1/auth")
//                .setAllowedOrigins("http://localhost:3000");
        // WebSocket endpoint
        registry.addEndpoint("/wss")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS(); // Use SockJS for fallback if needed

        // Additional HTTP endpoint for RESTful API
        registry.addEndpoint("/api/v1/auth")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }
}
