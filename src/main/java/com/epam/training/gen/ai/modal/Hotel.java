package com.epam.training.gen.ai.modal;

import com.microsoft.semantickernel.data.vectorstorage.annotations.VectorStoreRecordData;
import com.microsoft.semantickernel.data.vectorstorage.annotations.VectorStoreRecordKey;
import com.microsoft.semantickernel.data.vectorstorage.annotations.VectorStoreRecordVector;
import com.microsoft.semantickernel.data.vectorstorage.definition.DistanceFunction;
import com.microsoft.semantickernel.data.vectorstorage.definition.IndexKind;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class Hotel {

    @VectorStoreRecordKey
    private String hotelId;

    @VectorStoreRecordData(isFilterable = true)
    private String name;

    @VectorStoreRecordData(isFullTextSearchable = true)
    private String description;

    @VectorStoreRecordData(isFilterable = true)
    private List<String> tags;

    @VectorStoreRecordData(isFullTextSearchable = true)
    private List<String> activitiesAndLandmarks;

    @VectorStoreRecordVector(dimensions = 4, indexKind = IndexKind.HNSW, distanceFunction = DistanceFunction.COSINE_DISTANCE)
    private List<Float> hotelEmbedding;

    public Hotel() { }

    public Hotel(String hotelId, String name, String description, List<String> tags, List<String> activitiesAndLandmarks,
                 List<Float> descriptionEmbedding) {
        this.hotelId = hotelId;
        this.name = name;
        this.description = description;
        this.tags = Collections.unmodifiableList(tags);
        this.activitiesAndLandmarks = Collections.unmodifiableList(activitiesAndLandmarks);
        this.hotelEmbedding = Collections.unmodifiableList(descriptionEmbedding);
    }

    public void setHotelEmbedding(List<Float> hotelEmbedding) {
        this.hotelEmbedding = Collections.unmodifiableList(hotelEmbedding );
    }

    public void setActivitiesAndLandmarks(List<String> activitiesAndLandmarks) {
        this.activitiesAndLandmarks = Collections.unmodifiableList(activitiesAndLandmarks);
    }

    public String getHotelEmbeddingContent() {
        return String.join(", ", hotelEmbedding.stream()
                .map(String::valueOf)
                .toList());
    }
}