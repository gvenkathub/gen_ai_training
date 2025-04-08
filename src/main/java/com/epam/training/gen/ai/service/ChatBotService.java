package com.epam.training.gen.ai.service;

import com.epam.training.gen.ai.bean.HistoryBean;
import com.epam.training.gen.ai.config.KernelFactory;
import com.epam.training.gen.ai.dto.PromptRequest;
import com.epam.training.gen.ai.enums.SystemPrompts;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.services.ServiceNotFoundException;
import com.microsoft.semantickernel.services.chatcompletion.AuthorRole;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.training.gen.ai.util.PromptUtil.getSystemPromptForInventory;

/**
 * Service class for handling chat bot operations.
 */
@Slf4j
@Service
public class ChatBotService {

    @Autowired
    Kernel kernel;

    @Autowired
    Kernel inventoryKernel;

    @Autowired
    InvocationContext invocationContext;

    @Autowired
    InvocationContext invocationContextLowTemperature;

    @Autowired
    InvocationContext invocationContextHighTemperature;

    @Autowired
    HistoryBean historyBean;

    @Autowired
    KernelFactory kernelFactory;

    /**
     * Fetches the response for a given prompt from the chat bot.
     *
     * @param prompt the input prompt for the chat bot
     * @return the response from the chat bot
     * @throws ServiceNotFoundException if the chat completion service is not found
     */
    public String fetchPromptResponse(String prompt) throws ServiceNotFoundException {
        log.info("Fetching response for prompt: {}", prompt);
        var chatCompletionService = kernel.getService(ChatCompletionService.class);

        ChatHistory history = new ChatHistory();
        history.addUserMessage(prompt);

        List<ChatMessageContent<?>> results = chatCompletionService
                .getChatMessageContentsAsync(history, kernel, invocationContext)
                .block();

        return results.get(0).getContent();
    }

    /**
     * Fetches the conversation response for a given prompt request from the chat bot.
     *
     * @param promptRequest the input prompt request containing the prompt and other parameters
     * @return the response from the chat bot
     * @throws ServiceNotFoundException if the chat completion service is not found
     */
    public String fetchPromptResponse(PromptRequest promptRequest) throws ServiceNotFoundException {
        log.info("Fetching conversation response for prompt: {}", promptRequest.prompt());

        ChatHistory history = new ChatHistory();
        Map<String, ChatHistory> histories = historyBean.histories;

        // maintains chat history across multiple http requests.
        // requests conatining chat_id will be stored, retrieved and used in chatBot conversations.
        history = getChatHistory(promptRequest, history, histories);
        Kernel kernel = getKernelForModel(promptRequest);

        List<ChatMessageContent<?>> results = getChatMessageContentsForPromptRequest(promptRequest, history, kernel);

        // updates chat history and stores in map with chat_id as key
        // if chatId is not passed in request, chat history will not be stored
        updateChatHistory(promptRequest, history, histories, results);

        log.info("chatId {} , historySize: {}, histories: {}", promptRequest.chatId(),
                histories.get(promptRequest.chatId().toString()).getMessages().size(),
                histories.get(promptRequest.chatId().toString()).getMessages().toString());

        return results.get(0).getContent();
    }

    private List<ChatMessageContent<?>> getChatMessageContentsForPromptRequest(PromptRequest promptRequest, ChatHistory history, Kernel kernel) throws ServiceNotFoundException {
        var chatCompletionService = kernel.getService(ChatCompletionService.class);
        history.addUserMessage(promptRequest.prompt());
        return chatCompletionService
                .getChatMessageContentsAsync(history, kernel,
                        Boolean.TRUE.equals(promptRequest.random())
                                ? invocationContextHighTemperature
                                : invocationContextLowTemperature)
                .block();
    }

    private Kernel getKernelForModel(PromptRequest promptRequest) {
        return StringUtils.isNoneEmpty(promptRequest.modalType())
                ? kernelFactory.createKernel(promptRequest.modalType()) : inventoryKernel;
    }

    private static void updateChatHistory(PromptRequest promptRequest, ChatHistory history, Map<String, ChatHistory> histories, List<ChatMessageContent<?>> results) {
        history.addMessage(AuthorRole.ASSISTANT, results.get(0).getContent());
        histories.put(promptRequest.chatId().toString(), history);
    }

    private ChatHistory getChatHistory(PromptRequest promptRequest, ChatHistory history, Map<String, ChatHistory> histories) {
        if(histories.containsKey(promptRequest.chatId().toString())){
            history = histories.get(promptRequest.chatId().toString());
        } else {
            history.addMessage(AuthorRole.SYSTEM, getSystemPromptForInventory(promptRequest));
        }
        history.addMessage(AuthorRole.USER, promptRequest.prompt());
        return history;
    }

    public String fetchEvaluationReport(PromptRequest promptRequest) throws ServiceNotFoundException {
        var models = kernelFactory.getModels();
        List<String> responses = kernelFactory.getModels().stream()
                .map(model -> kernelFactory.createKernel(model))
                .map(kernel -> {
                        try {
                            return getChatMessageContentsForPromptRequest(promptRequest, new ChatHistory(), kernel).get(0).getContent();
                        } catch (ServiceNotFoundException e) {
                            throw new RuntimeException(e);
                        }
        }).collect(Collectors.toList());

        String prompt = String.format(SystemPrompts.EVALUATION.getPromptMessage(),
                promptRequest.prompt(),
                models.get(0)+" Response: "+responses.get(0),
                models.get(1)+" Response: "+responses.get(1),
                models.get(2)+" Response: "+responses.get(2));

        return fetchPromptResponse(prompt);
    }
}