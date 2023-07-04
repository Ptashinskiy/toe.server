package tic.tac.toe.service;

import org.springframework.stereotype.Service;
import tic.tac.toe.domain.Game;
import tic.tac.toe.dto.GameDto;

import java.util.HashMap;

@Service
public class GameService {

    private final HashMap<String, Game> games = new HashMap<>();

    public String startNewGame() {
        Game game = Game.newGame();
        games.put(game.id(), game);
        return game.id();
    }

    public GameDto makeMove(String gameId, int coordinate1, int coordinate2) {
        Game game = games.get(gameId);
        game.makeMove(coordinate1, coordinate2);
        GameDto dto = game.toDto();
        if (game.isFinished()) {
            games.remove(gameId);
        }
        return dto;
    }

    public GameDto getGame(String gameId) {
        return games.get(gameId).toDto();
    }
}
