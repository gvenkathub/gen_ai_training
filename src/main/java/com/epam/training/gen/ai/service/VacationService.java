package com.epam.training.gen.ai.service;

import com.epam.training.gen.ai.bean.HistoryBean;
import com.epam.training.gen.ai.dto.VacationDetails;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.services.ServiceNotFoundException;
import com.microsoft.semantickernel.services.chatcompletion.AuthorRole;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class VacationService {

    @Autowired
    Kernel vacationKernel;

    @Autowired
    InvocationContext invocationContextHighTemperature;

    private static String VACATION_PROMPT = "You are a vacation planner." +
            " Provide planning for the vacation considering hotel details from below description." +
            " ``description: %s``" +
            " Mention the Name of Hotel in back ticks (hotel name should be from the context provided), Keep the tone friendly and helpful." +
            " In The Planning, include details similar to below example: " +
            " ```Day1: Check-in at Hotel XYZ, explore the local area, dinner at ABC restaurant." +
            " Day2: Visit the beach, lunch at DEF cafe, evening stroll in the park." +
            " Day3: Check-out from Hotel XYZ, visit the local market, departure.```";

    public String fetchVacationDetails(VacationDetails vacationDetails) throws ServiceNotFoundException {
        log.info("Fetching vacation details for query: {}", vacationDetails.destination());

        ChatHistory history = new ChatHistory();
        history.addMessage(AuthorRole.SYSTEM,  String.format(VACATION_PROMPT, vacationDetails.destination()));
        history.addMessage(AuthorRole.USER, "Consider following parameters : "+vacationDetails.toString());

        var chatCompletionService = vacationKernel.getService(ChatCompletionService.class);
        return Optional.ofNullable(chatCompletionService
                .getChatMessageContentsAsync(history, vacationKernel, invocationContextHighTemperature)
                .block()).map(results -> results.get(0).getContent()).orElse(null);

    }
}
