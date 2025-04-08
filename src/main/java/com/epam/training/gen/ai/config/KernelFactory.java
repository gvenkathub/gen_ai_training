package com.epam.training.gen.ai.config;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KernelFactory {

    @Autowired
    private OpenAIAsyncClient openAIAsyncClient;

    @Value("${client-openai-modals}")
    List<String> models;

    public List<String> getModels(){
        return models;
    }

    public Kernel createKernel(String modelName) {
        return Kernel.builder()
                .withAIService(
                        ChatCompletionService.class,
                        OpenAIChatCompletion.builder()
                                .withModelId(getModalId(modelName))
                                .withOpenAIAsyncClient(openAIAsyncClient)
                                .build())
                .build();
    }

    private String getModalId(String modelName){
        return null != models && models.contains(modelName)
                ? modelName
                : "gpt-4-turbo";
    }

}
