package tic.tac.toe.dto;

/**
 * Data Transfer Object (DTO) class representing a request to get a game by its identifier.
 * The class encapsulates the game identifier.
 */
public class GetGameRequest {

    private String gameId;

    public GetGameRequest() {
    }

    public GetGameRequest(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
