package com.epam.training.gen.ai.util;

import com.epam.training.gen.ai.dto.PromptRequest;
import com.epam.training.gen.ai.enums.Inventories;
import com.epam.training.gen.ai.enums.SystemPrompts;
import com.microsoft.semantickernel.orchestration.PromptExecutionSettings;

/**
 * Utility class for building PromptExecutionSettings.
 */
public class PromptUtil {

    /**
     * Builds and returns a PromptExecutionSettings instance with predefined settings.
     *
     * @return a configured PromptExecutionSettings instance
     */
    public static PromptExecutionSettings buildPromptSettings(Integer maxTokens, Double temperature) {
        return PromptExecutionSettings.builder()
                .withTemperature(null == temperature ? 0.5 : temperature) // higher value, more creative
                .withMaxTokens(null == maxTokens ? 50 : maxTokens) // higher value, longer response
                .withTopP(0.9) // higher value, more diverse
                .withFrequencyPenalty(0.3) // higher value, less repetitive
                .withPresencePenalty(0.3) // higher value, less repetitive
                .build();
    }

    public static String getSystemPromptForInventory(PromptRequest promptRequest) {
        return Inventories.getInventoryType(promptRequest.inventoryType()).getInventoryMessage()
                .concat(SystemPrompts.CASUAL.getPromptMessage());
    }
}