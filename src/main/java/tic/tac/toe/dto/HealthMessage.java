package tic.tac.toe.dto;

public class HealthMessage {

    private String message;

    public HealthMessage() {
    }

    public HealthMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
