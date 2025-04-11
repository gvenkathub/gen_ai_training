package com.epam.training.gen.ai.modal;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// holds inventory of books and clothes in the cart
@Component
public class Cart {

    private List<Book> booksCartInventory = new ArrayList<>();

    private List<Cloth> clothesCartInventory = new ArrayList<>();

    public Cart() {
    }

    public List<Book> getBooksCartInventory() {
        return booksCartInventory;
    }

    public void setBooksCartInventory(List<Book> booksCartInventory) {
        this.booksCartInventory = booksCartInventory;
    }

    public List<Cloth> getClothesCartInventory() {
        return clothesCartInventory;
    }

    public void setClothesCartInventory(List<Cloth> clothesCartInventory) {
        this.clothesCartInventory = clothesCartInventory;
    }
}
