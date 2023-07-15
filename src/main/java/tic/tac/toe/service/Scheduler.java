package tic.tac.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tic.tac.toe.dto.Message;

@Component
public class Scheduler {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public Scheduler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedDelay = 60000L)
    public void healthMessaging() {
        messagingTemplate.convertAndSend("/game2/health", new Message("The server is running!"));
    }
}
