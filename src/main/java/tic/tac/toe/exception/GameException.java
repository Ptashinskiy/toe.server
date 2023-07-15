package tic.tac.toe.exception;

public class GameException extends RuntimeException {

    private final String gameId;

    private GameException(String message, String gameId) {
        super(message);
        this.gameId = gameId;
    }

    public static GameException thatMoveAlreadyBeenMade(String message, String gameId) {
        return new GameException(message, gameId);
    }

    public static GameException wrongCoordinates(String message, String gameId) {
        return new GameException(message, gameId);
    }

    public String gameId() {
        return gameId;
    }
}
