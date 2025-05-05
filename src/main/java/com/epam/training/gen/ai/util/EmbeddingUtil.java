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
                List.of("luxury", "beach", "spa", "Miami"), List.of("Miami Beach", "Art Deco Historic District", "Water Sports"),
                embeddingService.embed(List.of("Ocean Breeze Resort : A luxury beachside resort with stunning ocean views in Miami.", "Miami Beach, Art Deco Historic District, Water Sports"))));

        // generates 25 such embeddings for 10 cities covering India and Europe (can have multiple hotels from same cities). description should also contain city name.

        embeddings.add(new Hotel("2", "Mountain Retreat", "A cozy cabin in the Swiss Alps, perfect for skiing and hiking.",
                List.of("cabin", "skiing", "hiking", "Switzerland"), List.of("Swiss Alps", "Ski Resorts", "Hiking Trails"),
                embeddingService.embed(List.of("Mountain Retreat : A cozy cabin in the Swiss Alps, perfect for skiing and hiking.", "Swiss Alps, Ski Resorts, Hiking Trails"))));
        embeddings.add(new Hotel("3", "City Center Hotel", "A modern hotel located in the heart of Paris, close to major attractions.",
                List.of("modern", "city center", "Paris"), List.of("Eiffel Tower", "Louvre Museum", "Notre-Dame Cathedral"),
                embeddingService.embed(List.of("City Center Hotel : A modern hotel located in the heart of Paris, close to major attractions.", "Eiffel Tower, Louvre Museum, Notre-Dame Cathedral"))));
        embeddings.add(new Hotel("4", "Countryside Inn", "A charming inn surrounded by lush green fields in Tuscany, Italy.",
                List.of("charming", "inn", "Tuscany"), List.of("Tuscany", "Wine Tours", "Countryside"),
                embeddingService.embed(List.of("Countryside Inn : A charming inn surrounded by lush green fields in Tuscany, Italy.", "Tuscany, Wine Tours, Countryside"))));
        embeddings.add(new Hotel("5", "Desert Oasis", "A luxurious resort in the heart of the Sahara Desert, offering unique experiences.",
                List.of("luxurious", "desert", "oasis"), List.of("Sahara Desert", "Camel Rides", "Sand Dunes"),
                embeddingService.embed(List.of("Desert Oasis : A luxurious resort in the heart of the Sahara Desert, offering unique experiences.", "Sahara Desert, Camel Rides, Sand Dunes"))));
        embeddings.add(new Hotel("6", "Historic Castle Hotel", "A unique hotel set in a historic castle in Edinburgh, Scotland.",
                List.of("historic", "castle", "Scotland"), List.of("Edinburgh Castle", "Royal Mile", "Scottish History"),
                embeddingService.embed(List.of("A unique hotel set in a historic castle in Edinburgh, Scotland.", "Edinburgh Castle, Royal Mile, Scottish History"))));
        embeddings.add(new Hotel("7", "Tropical Paradise Resort", "An all-inclusive resort in the Maldives with private beaches and water villas.",
                List.of("all-inclusive", "tropical", "Maldives"), List.of("Maldives", "Private Beaches", "Water Villas"),
                embeddingService.embed(List.of("Tropical Paradise Resort : An all-inclusive resort in the Maldives with private beaches and water villas.", "Maldives, Private Beaches, Water Villas"))));
        embeddings.add(new Hotel("8", "Cultural Heritage Hotel", "A boutique hotel in Kyoto, Japan, showcasing traditional Japanese architecture.",
                List.of("boutique", "cultural", "Japan"), List.of("Kyoto", "Traditional Architecture", "Cultural Heritage"),
                embeddingService.embed(List.of("Cultural Heritage Hotel : A boutique hotel in Kyoto, Japan, showcasing traditional Japanese architecture.", "Kyoto, Traditional Architecture, Cultural Heritage"))));
        embeddings.add(new Hotel("9", "Luxury Safari Lodge", "An exclusive lodge in South Africa, offering safari experiences and wildlife tours.",
                List.of("exclusive", "safari", "South Africa"), List.of("Kruger National Park", "Wildlife Tours", "Luxury Safari"),
                embeddingService.embed(List.of("Luxury Safari Lodge : An exclusive lodge in South Africa, offering safari experiences and wildlife tours.", "Kruger National Park, Wildlife Tours, Luxury Safari"))));
        embeddings.add(new Hotel("10", "Artistic Boutique Hotel", "A stylish hotel in Barcelona, Spain, featuring contemporary art and design.",
                List.of("stylish", "artistic", "Spain"), List.of("Barcelona", "Contemporary Art", "Design"),
                embeddingService.embed(List.of("Artistic Boutique Hotel : A stylish hotel in Barcelona, Spain, featuring contemporary art and design.", "Barcelona, Contemporary Art, Design"))));

        embeddings.add(new Hotel("11", "Seaside Villa", "A private villa overlooking the Mediterranean Sea in Santorini, Greece.",
                List.of("private", "villa", "Greece"), List.of("Santorini", "Mediterranean Sea", "Luxury"),
                embeddingService.embed(List.of("Seaside Villa : A private villa overlooking the Mediterranean Sea in Santorini, Greece.", "Santorini, Mediterranean Sea, Luxury"))));
        embeddings.add(new Hotel("12", "Urban Loft Hotel", "A trendy loft-style hotel in New York City, perfect for city explorers.",
                List.of("trendy", "loft", "New York"), List.of("New York City", "Urban Exploration", "Trendy"),
                embeddingService.embed(List.of("Urban Loft Hotel : A trendy loft-style hotel in New York City, perfect for city explorers.", "New York City, Urban Exploration, Trendy"))));
        embeddings.add(new Hotel("13", "Beachfront Resort", "A family-friendly resort on the beaches of Cancun, Mexico.",
                List.of("family-friendly", "beachfront", "Mexico"), List.of("Cancun", "Family Activities", "Beachfront"),
                embeddingService.embed(List.of("Beachfront Resort : A family-friendly resort on the beaches of Cancun, Mexico.", "Cancun, Family Activities, Beachfront"))));
        embeddings.add(new Hotel("14", "Mountain Lodge", "A rustic lodge in the Rocky Mountains, ideal for outdoor enthusiasts.",
                List.of("rustic", "lodge", "Rocky Mountains"), List.of("Rocky Mountains", "Outdoor Activities", "Lodge"),
                embeddingService.embed(List.of("Mountain Lodge : A rustic lodge in the Rocky Mountains, ideal for outdoor enthusiasts.", "Rocky Mountains, Outdoor Activities, Lodge"))));
        embeddings.add(new Hotel("15", "Luxury Yacht Hotel", "A floating hotel experience on a luxury yacht in the Caribbean.",
                List.of("floating", "yacht", "Caribbean"), List.of("Caribbean", "Luxury Yacht", "Floating Hotel"),
                embeddingService.embed(List.of("Luxury Yacht Hotel : A floating hotel experience on a luxury yacht in the Caribbean.", "Caribbean, Luxury Yacht, Floating Hotel"))));
        embeddings.add(new Hotel("16", "Countryside Retreat", "A peaceful retreat in the countryside of Provence, France.",
                List.of("peaceful", "retreat", "France"), List.of("Provence", "Countryside", "Relaxation"),
                embeddingService.embed(List.of("Countryside Retreat : A peaceful retreat in the countryside of Provence, France.", "Provence, Countryside, Relaxation"))));
        embeddings.add(new Hotel("17", "Luxury Spa Resort", "A wellness resort in Bali, Indonesia, offering spa treatments and yoga.",
                List.of("wellness", "spa", "Bali"), List.of("Bali", "Spa Treatments", "Yoga"),
                embeddingService.embed(List.of("Luxury Spa Resort : A wellness resort in Bali, Indonesia, offering spa treatments and yoga.", "Bali, Spa Treatments, Yoga"))));
        embeddings.add(new Hotel("18", "Cultural City Hotel", "A hotel in Istanbul, Turkey, blending modern amenities with rich history.",
                List.of("modern", "cultural", "Turkey"), List.of("Istanbul", "Rich History", "Cultural Experience"),
                embeddingService.embed(List.of("Cultural City Hotel : A hotel in Istanbul, Turkey, blending modern amenities with rich history.", "Istanbul, Rich History, Cultural Experience"))));
        embeddings.add(new Hotel("19", "Luxury Vineyard Hotel", "A vineyard hotel in Napa Valley, California, offering wine tours and tastings.",
                List.of("vineyard", "luxury", "California"), List.of("Napa Valley", "Wine Tours", "Luxury"),
                embeddingService.embed(List.of("Luxury Vineyard Hotel : A vineyard hotel in Napa Valley, California, offering wine tours and tastings.", "Napa Valley, Wine Tours, Luxury"))));
        embeddings.add(new Hotel("20", "Historic City Hotel", "A historic hotel in Rome, Italy, close to ancient ruins and landmarks.",
                List.of("historic", "city", "Italy"), List.of("Rome", "Ancient Ruins", "Landmarks"),
                embeddingService.embed(List.of("Historic City Hotel : A historic hotel in Rome, Italy, close to ancient ruins and landmarks.", "Rome, Ancient Ruins, Landmarks"))));

        embeddings.add(new Hotel("21", "Luxury Ski Resort", "A high-end ski resort in Aspen, Colorado, with top-notch amenities.",
                List.of("high-end", "ski", "Colorado"), List.of("Aspen", "Skiing", "Luxury"),
                embeddingService.embed(List.of("Luxury Ski Resort : A high-end ski resort in Aspen, Colorado, with top-notch amenities.", "Aspen, Skiing, Luxury"))));
        embeddings.add(new Hotel("22", "Tropical Island Resort", "A private island resort in the Seychelles, perfect for relaxation.",
                List.of("private", "island", "Seychelles"), List.of("Seychelles", "Relaxation", "Tropical"),
                embeddingService.embed(List.of("Tropical Island Resort : A private island resort in the Seychelles, perfect for relaxation.", "Seychelles, Relaxation, Tropical"))));
        embeddings.add(new Hotel("23", "Luxury Urban Hotel", "A luxury hotel in Dubai, UAE, with stunning city views.",
                List.of("luxury", "urban", "UAE"), List.of("Dubai", "City Views", "Luxury"),
                embeddingService.embed(List.of("Luxury Urban Hotel : A luxury hotel in Dubai, UAE, with stunning city views.", "Dubai, City Views, Luxury"))));
        embeddings.add(new Hotel("24", "Countryside Farm Stay", "A farm stay experience in the countryside of Tuscany, Italy.",
                List.of("farm", "stay", "Italy"), List.of("Tuscany", "Farm Stay", "Countryside"),
                embeddingService.embed(List.of("Countryside Farm Stay : A farm stay experience in the countryside of Tuscany, Italy.", "Tuscany, Farm Stay, Countryside"))));
        embeddings.add(new Hotel("25", "Luxury Beachfront Villa", "A luxury villa on the beaches of Bora Bora, French Polynesia.",
                List.of("luxury", "beachfront", "French Polynesia"), List.of("Bora Bora", "Luxury Villa", "Beachfront"),
                embeddingService.embed(List.of("Luxury Beachfront Villa : A luxury villa on the beaches of Bora Bora, French Polynesia.", "Bora Bora, Luxury Villa, Beachfront"))));

        embeddings.add(new Hotel("26", "Royal Heritage Palace", "A luxurious palace hotel in Jaipur showcasing Indian royalty.",
                List.of("luxury", "palace", "Jaipur"), List.of("Jaipur", "Royalty", "Heritage"),
                embeddingService.embed(List.of("Royal Heritage Palace : A luxurious palace hotel in Jaipur showcasing Indian royalty.", "Jaipur, Royalty, Heritage"))));
        embeddings.add(new Hotel("27", "Spiritual Retreat", "A serene retreat in Rishikesh, India, perfect for yoga and meditation.",
                List.of("serene", "retreat", "Rishikesh"), List.of("Rishikesh", "Yoga", "Meditation"),
                embeddingService.embed(List.of("Spiritual Retreat : A serene retreat in Rishikesh, India, perfect for yoga and meditation.", "Rishikesh, Yoga, Meditation"))));
        embeddings.add(new Hotel("28", "Cultural Heritage Hotel", "A heritage hotel in Varanasi, India, offering a glimpse into Indian culture.",
                List.of("heritage", "culture", "Varanasi"), List.of("Varanasi", "Indian Culture", "Heritage"),
                embeddingService.embed(List.of("Cultural Heritage Hotel : A heritage hotel in Varanasi, India, offering a glimpse into Indian culture.", "Varanasi, Indian Culture, Heritage"))));
        embeddings.add(new Hotel("29", "Adventure Camp", "An adventure camp in Ladakh, India, offering trekking and camping experiences.",
                List.of("adventure", "camp", "Ladakh"), List.of("Ladakh", "Trekking", "Camping"),
                embeddingService.embed(List.of("Adventure Camp : An adventure camp in Ladakh, India, offering trekking and camping experiences.", "Ladakh, Trekking, Camping"))));
        embeddings.add(new Hotel("30", "Luxury Houseboat", "A luxury houseboat experience in Kerala, India, surrounded by backwaters.",
                List.of("luxury", "houseboat", "Kerala"), List.of("Kerala", "Backwaters", "Houseboat"),
                embeddingService.embed(List.of("A luxury houseboat experience in Kerala, India, surrounded by backwaters.", "Kerala, Backwaters, Houseboat"))));

        return embeddings;
    }
}
