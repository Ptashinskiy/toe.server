package tic.tac.toe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import tic.tac.toe.dto.GameDto;
import tic.tac.toe.dto.GetGameRequest;
import tic.tac.toe.dto.MakeMoveRequest;
import tic.tac.toe.service.GameService;

/**
 * Controller class that handles WebSocket message-based communication.
 * Exposes message mapping endpoints for making moves and retrieving game status.
 */
@Controller
public class MessageController {

    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Handles the WebSocket message mapping for making a move in the game.
     * Converts the request coordinates and delegates to the GameService to make the move.
     * Sends the updated game data to the appropriate WebSocket destination.
     *
     * @param request the MakeMoveRequest containing the game identifier and move coordinates
     */
    @MessageMapping("/make-move")
    public void makeMove(MakeMoveRequest request) {
        String coordinates = request.getCoordinates();
        Integer numberCoordinate = Integer.valueOf(String.valueOf(coordinates.charAt(1)));
        Character letterCoordinate = coordinates.charAt(0);
        int coordinate1 = -1;
        if (letterCoordinate.equals('A')) coordinate1 = 0;
        if (letterCoordinate.equals('B')) coordinate1 = 1;
        if (letterCoordinate.equals('C')) coordinate1 = 2;
        GameDto gameDto = gameService.makeMove(request.getGameId(),coordinate1, numberCoordinate - 1);
        messagingTemplate.convertAndSend("/game2/games/" + request.getGameId(), gameDto);
    }

    /**
     * Handles the WebSocket message mapping for retrieving the game status.
     * Delegates to the GameService to get the game data and sends it to the appropriate WebSocket destination.
     *
     * @param request the GetGameRequest containing the game identifier
     */
    @MessageMapping("/game-status")
    public void getGame(GetGameRequest request) {
        GameDto gameDto = gameService.getGame(request.getGameId());
        messagingTemplate.convertAndSend("/game2/status", gameDto);
    }
}
