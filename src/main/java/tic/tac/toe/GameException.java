package tic.tac.toe;

public class GameException extends RuntimeException {

    private GameException(String message) {
        super(message);
    }

    public static GameException thatMoveAlreadyBeenMade(String message) {
        return new GameException(message);
    }

    public static GameException wrongCoordinates(String message) {
        return new GameException(message);
    }
}
