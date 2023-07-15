package tic.tac.toe.dto;

/**
 * Data Transfer Object (DTO) class representing a request to make a move in the game.
 * The class encapsulates the game identifier and the coordinates of the move.
 */
public class MakeMoveRequest {

    private String gameId;

    private String coordinates;

    public MakeMoveRequest() {
    }

    public MakeMoveRequest(String gameId, String coordinates) {
        this.gameId = gameId;
        this.coordinates = coordinates;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}