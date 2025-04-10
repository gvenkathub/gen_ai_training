package com.epam.training.gen.ai.controller;

import com.azure.core.exception.HttpResponseException;
import com.epam.training.gen.ai.dto.PromptRequest;
import com.epam.training.gen.ai.dto.PromptResponse;
import com.epam.training.gen.ai.exception.ChatBotException;
import com.epam.training.gen.ai.service.ChatBotService;
import com.microsoft.semantickernel.services.ServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling chat bot related requests.
 */
@Slf4j
@RestController
@RequestMapping(path = "/chat-bot")
public class ChatBotController {

    @Autowired
    ChatBotService chatBotService;

    /**
     * Endpoint to get a response for a given prompt.
     *
     * @param prompt the input prompt for the chat bot
     * @return the response from the chat bot
     * @throws ServiceNotFoundException if the chat bot service is not found
     */
    @GetMapping(path = "/prompt")
    public ResponseEntity<PromptResponse> getPromptResponse(@RequestParam("prompt") String prompt) throws ChatBotException {
        try {
            return ResponseEntity.ok(new PromptResponse(chatBotService.fetchPromptResponse(prompt)));
        } catch (ServiceNotFoundException | HttpResponseException exception) {
            log.error("Error while processing prompt.", exception);
            throw new ChatBotException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        }
    }

    @PostMapping(path = "/converse")
    public ResponseEntity<String> getConversationResponse(@RequestBody PromptRequest promptRequest,
                                                          @RequestParam(value = "evaluate", required = false) boolean evaluate) throws ChatBotException {
        try {
            return ResponseEntity.ok(
                    Boolean.TRUE.equals(evaluate) ?
                            chatBotService.fetchEvaluationReport(promptRequest) :
                            chatBotService.fetchPromptResponse(promptRequest));
        } catch (ServiceNotFoundException | HttpResponseException exception) {
            log.error("Error while processing prompt.", exception);
            throw new ChatBotException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        }
    }
}