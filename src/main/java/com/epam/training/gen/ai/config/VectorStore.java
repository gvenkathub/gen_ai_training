package com.epam.training.gen.ai.config;

import com.epam.training.gen.ai.modal.Hotel;
import com.microsoft.semantickernel.data.VolatileVectorStoreRecordCollection;
import com.microsoft.semantickernel.data.VolatileVectorStoreRecordCollectionOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStore {

    /**
     * Creates a VolatileVectorStoreRecordCollection for Hotel objects.
     *
     * @return a VolatileVectorStoreRecordCollection for Hotel objects
     */
    @Bean
    public VolatileVectorStoreRecordCollection<Hotel> hotelVectorCollection() {
        var collection = new VolatileVectorStoreRecordCollection<>("skhotels",
                VolatileVectorStoreRecordCollectionOptions.<Hotel>builder()
                        .withRecordClass(Hotel.class)
                        .build());
        collection.createCollectionAsync().block();
        return collection;
    }

}
