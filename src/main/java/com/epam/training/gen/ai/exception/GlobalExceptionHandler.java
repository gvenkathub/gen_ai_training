package com.epam.training.gen.ai.exception;

import com.epam.training.gen.ai.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions across the whole application.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles general exceptions.
     *
     * @param ex the exception to handle
     * @return a ResponseEntity containing an ErrorResponse with the error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Exception while processing prompt.", ex);

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * Handles ChatBotException.
     *
     * @param cb_ex the ChatBotException to handle
     * @return a ResponseEntity containing an ErrorResponse with the error details
     */
    @ExceptionHandler(ChatBotException.class)
    public ResponseEntity<ErrorResponse> handleChatBotException(ChatBotException cb_ex) {
        log.error("ChatBot Exception while processing prompt. {}", cb_ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(cb_ex.getStatus(), cb_ex.getMessage()));
    }
}