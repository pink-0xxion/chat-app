package com.chat.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
// Marks this class as a configuration class, which tells Spring to treat it as a source of bean definitions.

@EnableWebSocketMessageBroker
// Enables WebSocket message handling in the application, allowing real-time communication.

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // The class implements WebSocketMessageBrokerConfigurer to configure WebSocket communication.

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This method registers the WebSocket endpoint (URL) for the client to connect to.

        registry.addEndpoint("/chat")
                // Defines the WebSocket endpoint URL that clients will connect to. Here, it's "/chat".

                .setAllowedOrigins("http://localhost:8080")
                // Sets which domain (origin) is allowed to connect to this endpoint. In this case, only "http://localhost:8080" is allowed.

                .withSockJS();
        // Enables SockJS fallback, ensuring that if WebSockets aren't supported, it falls back to other protocols (like HTTP-based).

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // This method configures the message broker for handling incoming and outgoing messages.

        //set message broker
        registry.enableSimpleBroker("/topic"); //topic is channel/group, subscribe
        // Enables a simple message broker that can send messages to destinations starting with "/topic".
        // This is used for broadcasting messages to groups (e.g., chat rooms).

        registry.setApplicationDestinationPrefixes("/app"); // expect message with /app/sendmessage
        // Sets the prefix "/app" for application-level destinations, where messages from clients will be directed for processing.
        // For example, when a client sends a message to "/app/sendmessage", the app will handle it.
    }
}


