package com.epam.training.gen.ai.plugins;

import com.epam.training.gen.ai.modal.Book;
import com.epam.training.gen.ai.util.PromptConstants;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class BookStorePlugin {

     private final static Map<String, List<Book>> booksInventory = PromptConstants.getBooksInventory();
     private Map<String, List<Book>> kartInventory = Map.of();

     @DefineKernelFunction(name = "get_books_by_category",
            description = "Get the list of existing clothes by categories.",
            returnType = "String")
    public Mono<String> getClothesByCategory() {
        return Mono.just(booksInventory.toString());
    }

}
