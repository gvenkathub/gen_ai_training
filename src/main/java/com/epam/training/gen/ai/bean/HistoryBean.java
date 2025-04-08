package com.epam.training.gen.ai.bean;

import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
* Holds ChatHistory in Bean Context
* Map value is updated and retrieved across http requests,
* To hold Chat Context for conversations
* */

@Component
public class HistoryBean {

    private HistoryBean() {
    }

    public static Map<String, ChatHistory> histories = new ConcurrentHashMap<>();
}
