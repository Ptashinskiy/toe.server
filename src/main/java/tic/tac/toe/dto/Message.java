package tic.tac.toe.dto;

/**
 * Data Transfer Object (DTO) class representing a generic message.
 * The class encapsulates a message string.
 */
public class Message {

    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
