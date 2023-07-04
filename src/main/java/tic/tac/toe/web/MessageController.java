package tic.tac.toe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import tic.tac.toe.dto.GameDto;
import tic.tac.toe.dto.GetGameRequest;
import tic.tac.toe.dto.MakeMoveRequest;
import tic.tac.toe.service.GameService;

@Controller
public class MessageController {

    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/make-move")
    public void makeMove(MakeMoveRequest request) {
        int coordinate1 = -1;
        String letterCoordinate = request.getLetterCoordinate();
        if (letterCoordinate.equals("A")) coordinate1 = 0;
        if (letterCoordinate.equals("B")) coordinate1 = 1;
        if (letterCoordinate.equals("C")) coordinate1 = 2;
        GameDto gameDto = gameService.makeMove(request.getGameId(),coordinate1, Integer.parseInt(request.getNumberCoordinate()) - 1);
        messagingTemplate.convertAndSend("/game2/games/" + request.getGameId(), gameDto);
    }

    @MessageMapping("/game-status")
    public void getGame(GetGameRequest request) {
        GameDto gameDto = gameService.getGame(request.getGameId());
        messagingTemplate.convertAndSend("/game2/status", gameDto);
    }
}
