package tic.tac.toe.service;

import org.springframework.stereotype.Service;
import tic.tac.toe.domain.Game;
import tic.tac.toe.dto.GameDto;

import java.util.HashMap;

/**
 * Service class that handles game-related operations.
 * Manages the creation of new games, making moves, and retrieving game data.
 */
@Service
public class GameService {

    /**
     * Simple storage for the game entities. Database imitation.
     * */
    private final HashMap<String, Game> games = new HashMap<>();

    /**
     * Starts a new game and returns its identifier.
     *
     * @return the identifier of the new game
     */
    public String startNewGame() {
        Game game = Game.newGame();
        games.put(game.id(), game);
        return game.id();
    }

    /**
     * Makes a move in the specified game and returns the updated game data.
     *
     * @param gameId      the identifier of the game
     * @param coordinate1 the first coordinate of the move
     * @param coordinate2 the second coordinate of the move
     * @return the updated game data as a GameDto object
     */
    public GameDto makeMove(String gameId, int coordinate1, int coordinate2) {
        Game game = games.get(gameId);
        game.makeMove(coordinate1, coordinate2);
        GameDto dto = game.toDto();
        if (game.isFinished()) {
            games.remove(gameId);
        }
        return dto;
    }

    /**
     * Retrieves the game data for the specified game.
     *
     * @param gameId the identifier of the game
     * @return the game data as a GameDto object
     */
    public GameDto getGame(String gameId) {
        return games.get(gameId).toDto();
    }
}
