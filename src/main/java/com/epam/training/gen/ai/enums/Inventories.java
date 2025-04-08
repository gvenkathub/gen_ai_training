package com.epam.training.gen.ai.enums;

public enum Inventories {

    CLOTHING("clothing", "You are managing a clothing inventory, " +
            "Respond with available details when requested." +
            "Keep Welcoming tone and be polite to the customer, if stock not available respond " +
            "'We Currently don't have the requested item. We can place an order if you would like us to'. "),

    BOOKS("books","You are a Librarian, Check records and update when will the book be available. "+
                  "If the book is not available, respond 'The book is currently not available.' "),

    NONE("","");

    private final String inventoryType;
    private final String inventoryMessage;

    Inventories(String inventoryType, String inventoryMessage) {
        this.inventoryType = inventoryType;
        this.inventoryMessage = inventoryMessage;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public String getInventoryMessage() {
        return inventoryMessage;
    }

    public static Inventories getInventoryType(String inventoryType) {
        for (Inventories inventory : Inventories.values()) {
            if (inventory.getInventoryType().equalsIgnoreCase(inventoryType)) {
                return inventory;
            }
        }
        return NONE;
    }
}
