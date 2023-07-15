package tic.tac.toe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tic.tac.toe.service.GameService;

/**
 * Controller class that handles game-related HTTP requests.
 * Exposes endpoints for creating new games.
 */
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Handles the HTTP POST request for creating a new game.
     *
     * @return a ResponseEntity with the HTTP status code 200 (OK) and the identifier of the new game
     */
    @PostMapping
    public ResponseEntity<String> createNewGame() {
        return ResponseEntity.ok(gameService.startNewGame());
    }
}
