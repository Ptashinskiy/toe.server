package tic.tac.toe.domain;

import tic.tac.toe.exception.GameException;
import tic.tac.toe.dto.GameDto;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * Represents the main entity for a Tic Tac Toe game.
 * The class stores and processes data related to the game, including moves and game status.
 */
public class Game {

    /**
     * Unique identifier of the game. Used to find a game from storage and send WebSocket messages.
     */
    private final String id;

    /**
     * 2D array representing the game field and moves made by players.
     * Each element represents a cell on the game board.
     */
    private final Sign[][] field;

    /**
     * The current status of the game (ACTIVE or FINISHED).
     */
    private GameStatus status;

    /**
     * The sign of the winner (X or O) if the game is finished.
     */
    private Sign winner;

    /**
     * Constructs a new Game object with the specified field, status, and winner.
     *
     * @param field  the 2D array representing the game field
     * @param status the current status of the game
     * @param winner the sign of the winner (null if no winner yet)
     */
    private Game(Sign[][] field, GameStatus status, Sign winner) {
        this.id = UUID.randomUUID().toString();
        this.field = field;
        this.status = status;
        this.winner = winner;
        for (Sign[] signs : field) {
            Arrays.fill(signs, Sign.N);
        }
    }

    /**
     * Creates a new instance of the Game class with an empty field and ACTIVE status.
     *
     * @return a new Game object
     */
    public static Game newGame() {
        return new Game(new Sign[3][3], GameStatus.ACTIVE, null);
    }

    /**
     * Returns the unique identifier of the game.
     *
     * @return the game's identifier
     */
    public String id() {
        return id;
    }

    /**
     * Converts the Game object to a GameDto object for transferring game data.
     *
     * @return the GameDto representation of the game
     */
    public GameDto toDto() {
        return new GameDto(id, field, status, winner);
    }

    /**
     * Makes a move in the game at the specified coordinates.
     * The move is made by the player, and then the bot makes a move.
     *
     * @param charCoordinate   the character coordinate (A, B, or C)
     * @param numberCoordinate the number coordinate (1, 2, or 3)
     * @throws GameException if the move coordinates are invalid or the move has already been made
     */
    public void makeMove(int charCoordinate, int numberCoordinate) {
        checkIfMoveIsCorrect(charCoordinate, numberCoordinate);
        field[charCoordinate][numberCoordinate] = Sign.X;
        botMove();
        checkIfGameIsFinished();
    }

    /**
     * Checks if the game is finished.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished() {
        return status == GameStatus.FINISHED;
    }

    /**
     * Checks if the move coordinates are correct and valid.
     *
     * @param charCoordinate   the character coordinate (A, B, or C)
     * @param numberCoordinate the number coordinate (1, 2, or 3)
     * @throws GameException if the move coordinates are invalid or the move has already been made
     */
    private void checkIfMoveIsCorrect(int charCoordinate, int numberCoordinate) {
        if (charCoordinate < 0 || charCoordinate > 2 || numberCoordinate < 0 || numberCoordinate > 2) {
            throw GameException.wrongCoordinates("Wrong coordinates!", id);
        } else if (field[charCoordinate][numberCoordinate] != Sign.N) {
            throw GameException.thatMoveAlreadyBeenMade("That move has already been made!", id);
        }
    }

    /**
     * Makes a move for the bot in the game.
     * The bot selects a random empty cell on the game board and makes a move.
     */
    private void botMove() {
        Random random = new Random();
        int randomCoordinate1 = random.nextInt(3);
        int randomCoordinate2 = random.nextInt(3);
        while (field[randomCoordinate1][randomCoordinate2] != Sign.N) {
            randomCoordinate1 = random.nextInt(3);
            randomCoordinate2 = random.nextInt(3);
        }
        field[randomCoordinate1][randomCoordinate2] = Sign.O;
    }

    /**
     * Checks if the game is finished and determines the winner.
     * If there is a winner, the game status is set to FINISHED and the winner is assigned.
     */
    private void checkIfGameIsFinished() {
        Sign diagonalWinnerSign = checkDiagonalLines();
        Sign horizontalWinnerSign = checkHorizontalLines();
        Sign verticalWinnerSign = checkVerticalLines();
        if (diagonalWinnerSign != Sign.N) {
            status = GameStatus.FINISHED;
            winner = diagonalWinnerSign;
        } else if (horizontalWinnerSign != Sign.N) {
            status = GameStatus.FINISHED;
            winner = horizontalWinnerSign;
        } else if (verticalWinnerSign != Sign.N) {
            status = GameStatus.FINISHED;
            winner = verticalWinnerSign;
        }
    }

    /**
     * Checks for a winner in the vertical lines of the game board.
     *
     * @return the sign of the winner (X or O), or Sign.N if there is no winner
     */
    private Sign checkVerticalLines() {
        if (field[0][0] == field[1][0] && field[1][0] == field[2][0]) {
            return field[0][0];
        } else if (field[0][1] == field[1][1] && field[1][1] == field[2][1]) {
            return field[0][1];
        } else if (field[0][2] == field[1][2] && field[1][2] == field[2][2]) {
            return field[0][2];
        } else {
            return Sign.N;
        }
    }

    /**
     * Checks for a winner in the horizontal lines of the game board.
     *
     * @return the sign of the winner (X or O), or Sign.N if there is no winner
     */
    private Sign checkHorizontalLines() {
        if (field[0][0] == field[0][1] && field[0][1] == field[0][2]) {
            return field[0][0];
        } else if (field[1][0] == field[1][1] && field[1][1] == field[1][2]) {
            return field[1][0];
        } else if (field[2][0] == field[2][1] && field[2][1] == field[2][2]) {
            return field[2][0];
        } else {
            return Sign.N;
        }
    }

    /**
     * Checks for a winner in the diagonal lines of the game board.
     *
     * @return the sign of the winner (X or O), or Sign.N if there is no winner
     */
    private Sign checkDiagonalLines() {
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            return field[0][0];
        } else if (field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            return field[0][2];
        } else {
            return Sign.N;
        }
    }

    /**
     * Returns a string representation of the game board.
     *
     * @return the string representation of the game board
     */
    @Override
    public String toString() {
        return "    " + " 1 " + "  2 " + "  3 " + "\n" +
                " A   " + field[0][0] + " | " + field[0][1] + " | " + field[0][2] + "\n" +
                "   ____|___|____\n" +
                " B   " + field[1][0] + " | " + field[1][1] + " | " + field[1][2] + "\n" +
                "   ____|___|____\n" +
                " C   " + field[2][0] + " | " + field[2][1] + " | " + field[2][2];
    }
}
