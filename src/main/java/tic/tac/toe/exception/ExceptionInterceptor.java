package tic.tac.toe.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tic.tac.toe.dto.Message;


/**
 * Exception interceptor class that handles GameException and sends error messages through WebSocket.
 * This class intercepts exceptions thrown during the request processing and converts them into messages
 * to be sent to the appropriate WebSocket destination.
 */
@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ExceptionInterceptor(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Handles GameException and sends an error message through WebSocket.
     * Converts the exception into a Message object and sends it to the appropriate WebSocket destination.
     *
     * @param exception the GameException to handle
     */
    @MessageExceptionHandler
    public void handleException(GameException exception) {
        messagingTemplate.convertAndSend("/game2/games/" + exception.gameId() + "/exceptions", new Message(exception.getMessage()));
    }
}
