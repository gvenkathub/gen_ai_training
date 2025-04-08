package com.epam.training.gen.ai.exception;

import lombok.Data;

/**
 * Custom exception class for handling chat bot related errors.
 */
@Data
public class ChatBotException extends Exception {

    private int status;

    /**
     * Constructs a new ChatBotException with the specified status and detail message.
     *
     * @param status the HTTP status code associated with the error
     * @param message the detail message
     */
    public ChatBotException(int status, String message) {
        super(message);
        this.status = status;
    }
}