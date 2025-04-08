package com.epam.training.gen.ai.dto;

public record PromptRequest(
        Integer chatId,
        String prompt,
        String inventoryType,
        Boolean random,
        String modalType
) {}
