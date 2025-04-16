package com.epam.training.gen.ai.store;


import com.epam.training.gen.ai.modal.Book;
import com.epam.training.gen.ai.modal.Cloth;
import com.epam.training.gen.ai.util.PromptConstants;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Store {

    private Map<String, List<Book>> booksInventory;
    private Map<String, List<Cloth>> clothesInventory;

    public Store() {
        this.booksInventory = PromptConstants.getBooksInventory();
        this.clothesInventory = PromptConstants.getClothInventory();
    }
}
