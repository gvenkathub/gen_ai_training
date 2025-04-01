package com.epam.training.gen.ai.util;

import com.epam.training.gen.ai.modal.Book;
import com.epam.training.gen.ai.modal.Cloth;

import java.util.List;
import java.util.Map;

public class PromptConstants {

    public static Map<String, List<Cloth>> getClothInventory(){
        return Map.of(
                "Shirts", List.of(
                        new Cloth("Shirt", "White", "M", "Cotton", "Levis", "1000"),
                        new Cloth("Shirt", "Black", "L", "Cotton", "Levis", "1200"),
                        new Cloth("Shirt", "Blue", "S", "Cotton", "Levis", "800")
                ),
                "Trousers", List.of(
                        new Cloth("Trouser", "Black", "M", "Cotton", "Levis", "1500"),
                        new Cloth("Trouser", "Blue", "L", "Cotton", "Levis", "1700"),
                        new Cloth("Trouser", "Grey", "S", "Cotton", "Levis", "1300")
                ),
                "T-Shirts", List.of(
                        new Cloth("T-Shirt", "White", "M", "Cotton", "Levis", "800"),
                        new Cloth("T-Shirt", "Black", "L", "Cotton", "Levis", "1000"),
                        new Cloth("T-Shirt", "Blue", "S", "Cotton", "Levis", "600")
                )
        );
    }

    public static Map<String, List<Book>> getBooksInventory() {
        return Map.of(
                "Fiction", List.of(
                        new Book("Fiction", "The Alchemist", "Paulo Coelho", "HarperCollins", "500"),
                        new Book("Fiction", "The Da Vinci Code",    "Dan Brown", "Penguin", "600"),
                        new Book("Fiction", "The Kite Runner", "Khaled Hosseini", "Bloomsbury", "400")
                ),
                "Non-Fiction", List.of(
                        new Book("Non-Fiction", "Sapiens", "Yuval Noah Harari", "HarperCollins", "700"),
                        new Book("Non-Fiction", "The Lean Startup", "Eric Ries", "Penguin", "800"),
                        new Book("Non-Fiction", "The Power of Habit", "Charles Duhigg", "Bloomsbury", "600")
                ),
                "Science", List.of(
                        new Book("Science", "A Brief History of Time", "Stephen Hawking", "HarperCollins", "900"),
                        new Book("Science", "Cosmos", "Carl Sagan", "Penguin", "1000"),
                        new Book("Science", "The Selfish Gene", "Richard Dawkins", "Bloomsbury", "800")
                )
        );
    }
}
