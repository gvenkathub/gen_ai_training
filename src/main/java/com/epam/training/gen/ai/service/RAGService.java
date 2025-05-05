package com.epam.training.gen.ai.service;

import com.epam.training.gen.ai.modal.Hotel;
import com.epam.training.gen.ai.util.EmbeddingUtil;
import com.microsoft.semantickernel.data.VolatileVectorStoreRecordCollection;
import com.microsoft.semantickernel.data.vectorstorage.options.VectorSearchOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class RAGService {

    @Autowired
    private VolatileVectorStoreRecordCollection<Hotel> hotelVectorCollection;

    @Autowired
    private EmbeddingService embeddingService;

    @Autowired
    private EmbeddingUtil embeddingUtil;

    /**
     * Creates an embedding for the given content and upserts it into the vector collection.
     *
     * @param content the content to create an embedding for
     * @return the ID of the created embedding
     */
    public String createEmbedding(String content) {
        List<Float> embedding = embeddingService.embed(List.of(content));

        // Upsert a record.
        var description = content;
        var hotelId = Integer.toString((int) (Math.random() * 100) + 10); //generates random hotel id after 10
        var hotel = new Hotel(hotelId, "Hotel "+hotelId, description, List.of("hotel"), List.of(), embedding);

        hotelVectorCollection.upsertAsync(hotel, null).block();

        // Retrieve the upserted record.
        Hotel retrievedHotel = hotelVectorCollection.getAsync(hotelId, null).block();
        return retrievedHotel.getHotelId();
    }

    /**
     * Searches for similar hotels based on the given query.
     *
     * @param query the search query
     * @return the most similar hotel
     */
    public Hotel search(String query) {
        // Generate a vector for your search text, using your chosen embedding generation implementation.
        // Just showing a placeholder method here for brevity.
        var searchVector = embeddingService.embed(List.of(query));

        // Do the search.
        var searchResult = hotelVectorCollection.searchAsync(searchVector,
                        VectorSearchOptions.builder()
                                .withTop(1)
                                .withVectorFieldName("hotelEmbedding")
                                .build())
                .block();

        assert searchResult != null;
        return searchResult.getResults().get(0).getRecord();
    }

    public Mono<Hotel> searchNonBlocking(String query) {
        return (Mono<Hotel>) embeddingService.embedNonBlocking(List.of(query))
                .map(searchVector -> {
                    // Do the search.
                    var searchResult = hotelVectorCollection.searchAsync(searchVector,
                            VectorSearchOptions.builder()
                                    .withTop(1)
                                    .withVectorFieldName("hotelEmbedding")
                                    .build());
                    return searchResult.map(
                            result -> {
                                return result.getResults().get(0).getRecord();
                            }
                    );
                }).flatMap(searchResult -> {
                    return searchResult;
                });
    }

    /**
     * Loads the embedding data into the vector collection.
     */
    public void loadData() {
        embeddingUtil.getHotelEmbeddings().forEach(
                hotel -> hotelVectorCollection.upsertAsync(hotel, null).block()
        );
    }

}
