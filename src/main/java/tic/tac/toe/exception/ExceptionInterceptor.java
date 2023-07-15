package tic.tac.toe.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tic.tac.toe.dto.Message;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ExceptionInterceptor(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageExceptionHandler
    public void handleException(GameException exception) {
        messagingTemplate.convertAndSend("/game2/games/" + exception.gameId() + "/exceptions", new Message(exception.getMessage()));
    }
}
