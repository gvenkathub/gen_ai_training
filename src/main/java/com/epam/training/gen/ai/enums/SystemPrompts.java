package com.epam.training.gen.ai.enums;

/*
 * Prompts to pass as a System message to get desired type of resposne from chat_bot
 */
public enum SystemPrompts {

    PROFESSIONAL("professional", "Generate response with professional tone and simple to understand. Use line breaks and bullets where ever needed."),
    CASUAL("casual", "Generate response with a casual tone. Keep it friendly and informal."),
    TECHNICAL("technical", "Generate response with technical details. Use jargon and technical terms appropriately."),

    EVALUATION("evaluate", "Evaluate the outputs of multiple chat generative engines (e.g., Response1, Response2, etc.) " +
                       "for the given input prompt. Assess each response across the following parameters:" +
                       " relevance, coherence, fluency, consistency, accuracy, helpfulness, biases, and hallucinations. " +
                       "Present the evaluation in a concise table where column 1 lists the parameters and subsequent columns contain qualitative ratings " +
                       "(e.g., 'Poor', 'Fair', 'Good'). Focus on identifying weaknesses, with ratings skewed toward 'Poor' where applicable. " +
                       "Conclude with a brief summary highlighting key issues, areas for improvement, and concerns. Provide 'NA' if evaluation does not apply." +
            "--- Input Prompt: %s" +
            "--- `%s`, `%s`, `%s` ");

    private final String promptType;
    private final String promptMessage;

    SystemPrompts(String promptType, String promptMessage) {
        this.promptType = promptType;
        this.promptMessage = promptMessage;
    }

    public String getPromptType() {
        return promptType;
    }

    public String getPromptMessage() {
        return promptMessage;
    }
}