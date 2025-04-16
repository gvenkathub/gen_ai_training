
package com.epam.training.gen.ai.util;

import com.epam.training.gen.ai.modal.Book;
import com.epam.training.gen.ai.modal.Cloth;

import java.util.List;
import java.util.Map;

public class PromptConstants {

    public static Map<String, List<Cloth>> getClothInventory(){
        return Map.of(
                "Shirts", List.of(
                        new Cloth("Shirt", "White", "M", "Cotton", "Levis", "1000", 10),
                        new Cloth("Shirt", "Black", "L", "Cotton", "Levis", "1200", 5),
                        new Cloth("Shirt", "Blue", "S", "Cotton", "Levis", "800", 8)
                ),
                "Trousers", List.of(
                        new Cloth("Trouser", "Black", "M", "Cotton", "Levis", "1500", 10),
                        new Cloth("Trouser", "Blue", "L", "Cotton", "Levis", "1700", 5),
                        new Cloth("Trouser", "Grey", "S", "Cotton", "Levis", "1300", 8)
                ),
                "T-Shirts", List.of(
                        new Cloth("T-Shirt", "White", "M", "Cotton", "Levis", "800", 10),
                        new Cloth("T-Shirt", "Black", "L", "Cotton", "Levis", "1000", 5),
                        new Cloth("T-Shirt", "Blue", "S", "Cotton", "Levis", "600", 8)
                )
        );
    }

    public static Map<String, List<Book>> getBooksInventory() {
        return Map.of(
                "Fiction", List.of(
                        new Book("Fiction", "The Alchemist", "Paulo Coelho", "HarperCollins", "500",10),
                        new Book("Fiction", "The Da Vinci Code",    "Dan Brown", "Penguin", "600",5),
                        new Book("Fiction", "The Kite Runner", "Khaled Hosseini", "Bloomsbury", "400",8)
                ),
                "Non-Fiction", List.of(
                        new Book("Non-Fiction", "Sapiens", "Yuval Noah Harari", "HarperCollins", "700",12),
                        new Book("Non-Fiction", "The Lean Startup", "Eric Ries", "Penguin", "800",15),
                        new Book("Non-Fiction", "The Power of Habit", "Charles Duhigg", "Bloomsbury", "600",10)
                ),
                "Science", List.of(
                        new Book("Science", "A Brief History of Time", "Stephen Hawking", "HarperCollins", "900",20),
                        new Book("Science", "Cosmos", "Carl Sagan", "Penguin", "1000",25),
                        new Book("Science", "The Selfish Gene", "Richard Dawkins", "Bloomsbury", "800",18)
                )
        );
    }
}

