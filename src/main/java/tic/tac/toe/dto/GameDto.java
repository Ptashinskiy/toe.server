package tic.tac.toe.dto;

import tic.tac.toe.domain.GameStatus;
import tic.tac.toe.domain.Sign;

public class GameDto {

    private String id;

    private Sign[][] field;

    private GameStatus status;

    private Sign winner;

    public GameDto() {
    }

    public GameDto(String id, Sign[][] field, GameStatus status, Sign winner) {
        this.id = id;
        this.field = field;
        this.status = status;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sign[][] getField() {
        return field;
    }

    public void setField(Sign[][] field) {
        this.field = field;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Sign getWinner() {
        return winner;
    }

    public void setWinner(Sign winner) {
        this.winner = winner;
    }
}
