package com.epam.training.gen.ai.plugins;

import com.epam.training.gen.ai.modal.Cart;
import com.epam.training.gen.ai.modal.Cloth;
import com.epam.training.gen.ai.store.Store;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class OrderWardrobePlugin {

    private Store store;

    private Map<String, List<Cloth>> clothesInventory;

    private Cart cart;

    public OrderWardrobePlugin() {
        this.store = new Store();
        this.clothesInventory = store.getClothesInventory();
        this.cart = new Cart();
    }

    @DefineKernelFunction(name = "get_clothes_by_category",
            description = "Get the list of existing clothes by categories.",
            returnType = "String")
    public Mono<String> getClothesByCategory() {
        return Mono.just(clothesInventory.toString());
    }

    @DefineKernelFunction(name = "add_cloth_to_cart",
            description = "Add a cloth to the cart and return List of clothes in cart as String",
            returnType = "String")
    public Mono<String> addClothToCart(String clothName, String color) {
        log.info("Adding cloth to cart: " + clothName);
        log.info("Clothes Inventory: " + clothesInventory.size());
        List<Cloth> kartInventory = new ArrayList<>();

        String clothCategory = getClothCategory(clothName);
        if (clothCategory != null) {
            kartInventory.add(getClothFromStore(color, clothCategory));
            cart.setClothesCartInventory(kartInventory);
            log.info("Cloth added to cart: " + clothName);
        } else {
            log.info("Cloth not found: " + clothName);
        }
        return Mono.just(cart.getClothesCartInventory().toString());
    }

    private Cloth getClothFromStore(String color, String clothCategory) {
        return clothesInventory.get(clothCategory).stream()
                .filter(cloth -> cloth.color().equals(color))
                .findFirst()
                .orElse(clothesInventory.get(clothCategory).get(0));
    }

    private String getClothCategory(String clothName) {
        for (Map.Entry<String, List<Cloth>> entry : clothesInventory.entrySet()) {
            for (Cloth cloth : entry.getValue()) {
                if (cloth.garmentType().equals(clothName) && cloth.quantity() > 0) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    @DefineKernelFunction(name = "remove_cloth_from_cart",
            description = "Remove a cloth from the cart and returns list of clothes in the cart.",
            returnType = "String")
    public Mono<String> removeClothFromCart(String clothName, String color) {
        var kartInventory = cart.getClothesCartInventory();
        kartInventory.stream()
                .filter(cloth -> cloth.garmentType().equals(clothName))
                .findFirst()
                .map(book -> kartInventory.remove(book))
                .orElseGet(() -> {
                    log.info("Cloth not found in cart: " + clothName);
                    return null;
                });
        return Mono.just(cart.getClothesCartInventory().toString());
    }

    //fetch items from kart
    @DefineKernelFunction(name = "fetch_items_from_cart",
            description = "Fetch items from the cart for books.",
            returnType = "String")
    public Mono<String> fetchItemsFromCart() {
        StringBuilder items = new StringBuilder("Items in cart:\n");
        for (Cloth cloth : cart.getClothesCartInventory()) {
            items.append(cloth.garmentType()).append("\n");
        }
        return Mono.just(items.toString());
    }
}

