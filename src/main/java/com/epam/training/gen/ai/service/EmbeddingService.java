package com.epam.training.gen.ai.service;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.EmbeddingsOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class EmbeddingService {

    @Autowired
    private OpenAIAsyncClient openAIAsyncClient;

    @Value("${client-openai-embedding-modal}")
    private String embeddingModal;

    /**
     * Creates an embedding for the given content.
     *
     * @param embeddingContent the content to create an embedding for
     * @return a List of Float representing the embedding
     */
    public List<Float> embed(List<String> embeddingContent) {
        var embeddings = openAIAsyncClient
                .getEmbeddings(embeddingModal,
                        new EmbeddingsOptions(embeddingContent)).block();
        return Optional.ofNullable(embeddings)
                .map(e -> e.getData().get(0).getEmbedding())
                .orElse(null);
    }
    public Mono<List<Float>> embedNonBlocking(List<String> embeddingContent) {
        return openAIAsyncClient
                .getEmbeddings(embeddingModal,
                        new EmbeddingsOptions(embeddingContent)).map(embeddings ->
                        Optional.ofNullable(embeddings)
                                .map(e -> e.getData().get(0).getEmbedding())
                                .orElse(null));
    }

}
