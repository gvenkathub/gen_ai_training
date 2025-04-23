package com.epam.training.gen.ai.util;

import com.epam.training.gen.ai.modal.Hotel;
import com.epam.training.gen.ai.service.EmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmbeddingUtil {

    @Autowired
    private EmbeddingService embeddingService;

    /**
     * Generates a list of hotel embeddings with descriptions and tags.
     *
     * @return a list of Hotel objects with embeddings
     */
    public List<Hotel> getHotelEmbeddings() {
        List<Hotel> embeddings = new ArrayList<>();

        embeddings.add(new Hotel("1", "Ocean Breeze Resort", "A luxury beachside resort with stunning ocean views in Miami.",
                embeddingService.embed("A luxury beachside resort with stunning ocean views in Miami."), List.of("luxury", "beach", "spa", "Miami")));
        embeddings.add(new Hotel("2", "Mountain Escape Lodge", "A serene retreat nestled in the mountains of Denver, perfect for adventure seekers.",
                embeddingService.embed("A serene retreat nestled in the mountains of Denver, perfect for adventure seekers."), List.of("mountain", "adventure", "family", "Denver")));
        embeddings.add(new Hotel("3", "City Lights Hotel", "A modern hotel in the heart of New York City, ideal for business travelers.",
                embeddingService.embed("A modern hotel in the heart of New York City, ideal for business travelers."), List.of("city", "business", "luxury", "New York")));
        embeddings.add(new Hotel("4", "Romantic Getaway Inn", "A charming inn in Paris designed for couples seeking a romantic escape.",
                embeddingService.embed("A charming inn in Paris designed for couples seeking a romantic escape."), List.of("romantic", "spa", "luxury", "Paris")));
        embeddings.add(new Hotel("5", "Family Fun Resort", "A family-friendly resort with activities for all ages in Orlando.",
                embeddingService.embed("A family-friendly resort with activities for all ages in Orlando."), List.of("family", "pool", "adventure", "Orlando")));
        embeddings.add(new Hotel("6", "Desert Oasis Retreat", "A tranquil retreat in the desert of Phoenix with unique spa experiences.",
                embeddingService.embed("A tranquil retreat in the desert of Phoenix with unique spa experiences."), List.of("spa", "luxury", "adventure", "Phoenix")));
        embeddings.add(new Hotel("7", "Lakeside Paradise", "A peaceful lakeside hotel in Minneapolis offering water activities and relaxation.",
                embeddingService.embed("A peaceful lakeside hotel in Minneapolis offering water activities and relaxation."), List.of("lake", "family", "romantic", "Minneapolis")));
        embeddings.add(new Hotel("8", "Business Hub Hotel", "A hotel tailored for business professionals with modern amenities in San Francisco.",
                embeddingService.embed("A hotel tailored for business professionals with modern amenities in San Francisco."), List.of("business", "city", "luxury", "San Francisco")));
        embeddings.add(new Hotel("9", "Adventure Base Camp", "A base camp for outdoor enthusiasts near hiking and biking trails in Boulder.",
                embeddingService.embed("A base camp for outdoor enthusiasts near hiking and biking trails in Boulder."), List.of("adventure", "mountain", "family", "Boulder")));
        embeddings.add(new Hotel("10", "Tropical Paradise Resort", "A tropical resort with lush gardens and private beaches in Honolulu.",
                embeddingService.embed("A tropical resort with lush gardens and private beaches in Honolulu."), List.of("beach", "luxury", "romantic", "Honolulu")));

        return embeddings;
    }
}
