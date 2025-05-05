package com.epam.training.gen.ai.plugins;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.epam.training.gen.ai.modal.Hotel;
import com.epam.training.gen.ai.service.EmbeddingService;
import com.epam.training.gen.ai.service.RAGService;
import com.microsoft.semantickernel.data.VolatileVectorStoreRecordCollection;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@NoArgsConstructor
public class VacationPlannerPlugin {

    @Autowired
    RAGService ragService;

    @Autowired
    OpenAIAsyncClient openAIAsyncClient;

    @DefineKernelFunction(name = "get_hotels_for_vacation",
            description = "Get the list of hotels that match the description.",
            returnType = "String")
    public Mono<String> getHotelsForVacation(String description) {
        return ragService.searchNonBlocking(description).map(
                result -> {
                    var hotel = ((Hotel) result).getDescription();
                    log.info("inside plugin for Hotel Embeddings "+hotel);
                    return result.toString();
                });
    }

}
