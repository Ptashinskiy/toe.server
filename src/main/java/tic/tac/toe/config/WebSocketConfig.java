package tic.tac.toe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * Configuration class for WebSocket messaging.
 * Enables WebSocket message handling and configures the message broker and endpoints.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker registry.
     * Enables a simple message broker for the specified destination prefix ("/game2").
     *
     * @param registry the message broker registry
     */
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/game2");
        registry.setApplicationDestinationPrefixes("/game");
    }

    /**
     * Registers the STOMP endpoints and sets the handshake handler.
     * Adds the "/tic-tac-toe" endpoint for WebSocket connections.
     *
     * @param registry the STOMP endpoint registry
     */
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/tic-tac-toe")
                .setHandshakeHandler(new DefaultHandshakeHandler());
    }
}
