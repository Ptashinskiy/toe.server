package tic.tac.toe.exception;

/**
 * Custom exception class for game-related exceptions.
 * Represents exceptions that can occur during Tic Tac Toe game processing.
 */
public class GameException extends RuntimeException {

    private final String gameId;

    /**
     * Constructs a GameException with the specified error message and game identifier.
     *
     * @param message the error message describing the exception
     * @param gameId  the identifier of the game associated with the exception
     */
    private GameException(String message, String gameId) {
        super(message);
        this.gameId = gameId;
    }

    /**
     * Creates a GameException for an already made move with the specified error message and game identifier.
     *
     * @param message the error message describing the exception
     * @param gameId  the identifier of the game associated with the exception
     * @return a GameException for an already made move
     */
    public static GameException thatMoveAlreadyBeenMade(String message, String gameId) {
        return new GameException(message, gameId);
    }

    /**
     * Creates a GameException for wrong coordinates with the specified error message and game identifier.
     *
     * @param message the error message describing the exception
     * @param gameId  the identifier of the game associated with the exception
     * @return a GameException for wrong coordinates
     */
    public static GameException wrongCoordinates(String message, String gameId) {
        return new GameException(message, gameId);
    }

    /**
     * Returns the identifier of the game associated with the exception.
     *
     * @return the identifier of the game
     */
    public String gameId() {
        return gameId;
    }
}
