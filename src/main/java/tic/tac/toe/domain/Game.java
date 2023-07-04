package tic.tac.toe.domain;

import tic.tac.toe.GameException;
import tic.tac.toe.dto.GameDto;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Game {

    private final String id;

    private final Sign[][] field;

    private GameStatus status;

    private Sign winner;

    private Game(Sign[][] field, GameStatus status, Sign winner) {
        this.id = UUID.randomUUID().toString();
        this.field = field;
        this.status = status;
        this.winner = winner;
        for (Sign[] signs : field) {
            Arrays.fill(signs, Sign.N);
        }
    }

    public static Game newGame() {
        return new Game(new Sign[3][3], GameStatus.ACTIVE, null);
    }

    public String id() {
        return id;
    }

    public GameDto toDto() {
        return new GameDto(id, field, status, winner);
    }

    public void makeMove(int charCoordinate, int numberCoordinate) {
        checkIfMoveIsCorrect(charCoordinate, numberCoordinate);
        field[charCoordinate][numberCoordinate] = Sign.X;
        botMove();
        checkIfGameIsFinished();
    }

    public boolean isFinished() {
        return status == GameStatus.FINISHED;
    }

    private void checkIfMoveIsCorrect(int charCoordinate, int numberCoordinate) {
        if (charCoordinate < 0 || charCoordinate > 2 || numberCoordinate < 0 || numberCoordinate > 2) {
            throw GameException.wrongCoordinates("Wrong coordinates!");
            //System.out.println("Incorrect coordinates!");
        } else if (field[charCoordinate][numberCoordinate] != Sign.N) {
            throw GameException.thatMoveAlreadyBeenMade("That move already been made!");
            //System.out.println("That move already been made;");
        }
    }

    private void botMove() {
        Random random = new Random();
        int randomCoordinate1 = random.nextInt(0,3);
        int randomCoordinate2 = random.nextInt(0,3);
        while (field[randomCoordinate1][randomCoordinate2] != Sign.N) {
            randomCoordinate1 = random.nextInt(0,3);
            randomCoordinate2 = random.nextInt(0,3);
        }
        field[randomCoordinate1][randomCoordinate2] = Sign.O;
    }

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

    private Sign checkVerticalLines() {
        if (field[0][0] == field[1][0] && field[1][0] == field[2][0]) {
            return field[0][0];
        } else if (field[0][1] == field[1][1] && field[1][1] == field[2][1]) {
            return field[0][1];
        } else if (field[0][2] == field[1][2] && field[1][2] == field[2][2]) {
            return field[0][2];
        } else return Sign.N;
    }

    private Sign checkHorizontalLines() {
        if (field[0][0] == field[0][1] && field[0][1] == field[0][2]) {
            return field[0][0];
        } else if (field[1][0] == field[1][1] && field[1][1] == field[1][2]) {
            return field[1][0];
        } else if (field[2][0] == field[2][1] && field[2][1] == field[2][2]) {
            return field[2][0];
        } else return Sign.N;
    }

    private Sign checkDiagonalLines() {
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            return field[0][0];
        } else if (field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            return field[0][2];
        } else return Sign.N;
    }

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
