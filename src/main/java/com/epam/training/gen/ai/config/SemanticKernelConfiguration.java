package com.epam.training.gen.ai.config;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.InvocationReturnMode;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.epam.training.gen.ai.util.PromptUtil.buildPromptSettings;

/**
 * Configuration class for setting up Semantic Kernel components.
 */
@Configuration
public class SemanticKernelConfiguration {

    @Value("${client-openai-key}")
    private String apiKey;

    @Value("${client-openai-endpoint}")
    private String endpoint;

    @Value("${client-openai-deployment-name}")
    private String modelName;

    /**
     * Creates an OpenAIAsyncClient bean.
     *
     * @return the OpenAIAsyncClient instance
     */
    @Bean
    public OpenAIAsyncClient openAIAsyncClient() {
        return new OpenAIClientBuilder()
                .credential(new KeyCredential(apiKey))
                .endpoint(endpoint)
                .buildAsyncClient();
    }

    /**
     * Creates a ChatCompletionService bean.
     *
     * @param openAIAsyncClient the OpenAIAsyncClient instance
     * @return the ChatCompletionService instance
     */
    @Bean
    public ChatCompletionService chatCompletionService(OpenAIAsyncClient openAIAsyncClient){
        return OpenAIChatCompletion.builder()
                .withModelId(modelName)
                .withOpenAIAsyncClient(openAIAsyncClient)
                .build();
    }

    /**
     * Creates a Kernel bean.
     *
     * @param chatCompletionService the ChatCompletionService instance
     * @return the Kernel instance
     */
    @Bean
    public Kernel kernel(ChatCompletionService chatCompletionService){
        return Kernel.builder()
                .withAIService(ChatCompletionService.class, chatCompletionService)
                .build();
    }

    @Bean
    public Kernel inventoryKernel(ChatCompletionService chatCompletionService) {
        KernelPlugin pluginWardrobe = KernelPluginFactory.createFromObject(
                new OrderWardrobePlugin(), "OrderWardrobePlugin");

        KernelPlugin pluginBookstore = KernelPluginFactory.createFromObject(
                new BookStorePlugin(), "BookStorePlugin");

        return Kernel.builder()
                .withAIService(ChatCompletionService.class, chatCompletionService)
                .withPlugin(pluginWardrobe)
                .withPlugin(pluginBookstore)
                .build();
    }


    /**
     * Creates an InvocationContext bean.
     *
     * @return the InvocationContext instance
     */
    @Bean
    public InvocationContext invocationContext(){
        return new InvocationContext.Builder()
                .withReturnMode(InvocationReturnMode.LAST_MESSAGE_ONLY)
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                .withPromptExecutionSettings(buildPromptSettings(300, 0.1))
                .build();
    }

    /*
    * Customized context to generate more random responses
    * */
    @Bean
    public InvocationContext invocationContextHighTemperature(){
        return new InvocationContext.Builder()
                .withReturnMode(InvocationReturnMode.LAST_MESSAGE_ONLY)
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                .withPromptExecutionSettings(buildPromptSettings(100, 0.9))
                .build();
    }

    /*
     * Customized context to generate less random responses
     * */
    @Bean
    public InvocationContext invocationContextLowTemperature(){
        return new InvocationContext.Builder()
                .withReturnMode(InvocationReturnMode.LAST_MESSAGE_ONLY)
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                .withPromptExecutionSettings(buildPromptSettings(100, 0.2))
                .build();
    }
}