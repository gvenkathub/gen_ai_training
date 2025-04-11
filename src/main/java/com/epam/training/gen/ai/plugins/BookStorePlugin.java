package com.epam.training.gen.ai.plugins;

import com.epam.training.gen.ai.modal.Book;
import com.epam.training.gen.ai.modal.Cart;
import com.epam.training.gen.ai.modal.Cloth;
import com.epam.training.gen.ai.store.Store;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Component
@Slf4j
public class BookStorePlugin {

    private Store store;

    private Map<String, List<Book>> booksInventory;

    private Cart cart;

    public BookStorePlugin() {
        this.store = new Store();
        this.booksInventory = store.getBooksInventory();
        this.cart = new Cart();
    }

    @DefineKernelFunction(name = "get_books_by_category",
            description = "Get the list of existing clothes by categories.",
            returnType = "String")
    public Mono<String> getClothesByCategory() {
        return Mono.just(booksInventory.toString());
    }

    @DefineKernelFunction(name = "add_book_to_cart",
            description = "Add a book to the cart, and returns list of books in cart as String.",
            returnType = "String")
    public Mono<String> addBookToCart(String bookName) {
        log.info("Adding book to cart: " + bookName);
        log.info("Books Inventory: " + booksInventory.size());
        List<Book> kartInventory = new ArrayList<>();

        String bookCategory = getBookCategory(bookName);
        if (bookCategory != null) {
            kartInventory.add(getBookFromStore(bookName, bookCategory));
            cart.setBooksCartInventory(kartInventory);
            log.info("Book added to cart: " + bookName);
        } else {
            log.info("Book not found: " + bookName);
        }
        return Mono.just(cart.getBooksCartInventory().toString());
    }

    private Book getBookFromStore(String bookName, String bookCategory) {
        return booksInventory.get(bookCategory).stream()
                .filter(book -> book.title().equals(bookName))
                .findFirst()
                .orElse(booksInventory.get(bookCategory).get(0));
    }

    private String getBookCategory(String bookName) {
        for (Map.Entry<String, List<Book>> entry : booksInventory.entrySet()) {
            for (Book book : entry.getValue()) {
                if (book.title().equals(bookName)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    @DefineKernelFunction(name = "remove_book_from_cart",
            description = "Remove a book from the cart and returns list of books in cart as string.",
            returnType = "String")
    public Mono<String> removeBookFromCart(String bookName) {
        var kartInventory = cart.getBooksCartInventory();
        kartInventory.stream()
                .filter(book -> book.title().equals(bookName))
                .findFirst()
                .map(book -> kartInventory.remove(book))
                .orElseGet(() -> {
                    log.info("Book not found in cart: " + bookName);
                    return null;
                });
        cart.setBooksCartInventory(kartInventory);
        return Mono.just(cart.getBooksCartInventory().toString());
    }


    //generate bill with total cost (get price from book object)
    @DefineKernelFunction(name = "generate_bill",
            description = "Generate a bill for the items in the cart.",
            returnType = "String")
    public Mono<String> generateBill() {
        double totalCost = 0.0;
        StringBuilder bill = new StringBuilder("Bill:\n");
        for (Book book : cart.getBooksCartInventory()) {
            totalCost += Double.parseDouble(book.price());
            bill.append(book.title()).append(" - ").append(book.price()).append("\n");
        }
        for (Cloth cloth : cart.getClothesCartInventory()) {
            totalCost += Double.parseDouble(cloth.price());
            bill.append(cloth.garmentType()).append(" - ").append(cloth.price()).append("\n");
        }
        bill.append("Total Cost: ").append(totalCost);
        return Mono.just(bill.toString());
    }

    //fetch items from kart
    @DefineKernelFunction(name = "fetch_items_from_cart",
            description = "Fetch items from the cart for books.",
            returnType = "String")
    public Mono<String> fetchItemsFromCart() {
        StringBuilder items = new StringBuilder("Items in cart:\n");
        for (Book book : cart.getBooksCartInventory()) {
            items.append(book.title()).append("\n");
        }
        return Mono.just(items.toString());
    }

}
