package tic.tac.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tic.tac.toe.dto.Message;

/**
 * Scheduler component that sends periodic health messages over WebSocket.
 * This component is responsible for sending a health message indicating that the server is running.
 */
@Component
public class Scheduler {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public Scheduler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Sends a health message periodically.
     * This method is scheduled to run at a fixed delay of 60,000 milliseconds (1 minute).
     */
    @Scheduled(fixedDelay = 60000L)
    public void healthMessaging() {
        messagingTemplate.convertAndSend("/game2/health", new Message("The server is running!"));
    }
}
