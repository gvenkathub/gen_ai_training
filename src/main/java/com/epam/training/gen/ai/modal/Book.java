package com.epam.training.gen.ai.modal;

public record Book(
        String genre,
        String title,
        String author,
        String publisher,
        String price,
        Integer quantity
) {
}
