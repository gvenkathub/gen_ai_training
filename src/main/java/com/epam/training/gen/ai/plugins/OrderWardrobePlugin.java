package com.epam.training.gen.ai.plugins;

import com.epam.training.gen.ai.modal.Cloth;
import com.epam.training.gen.ai.util.PromptConstants;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class OrderWardrobePlugin {

    private final static Map<String, List<Cloth>> clothesInventory = PromptConstants.getClothInventory();
    private Map<String, List<Cloth>> kartInventory = Map.of();

    @DefineKernelFunction(name = "get_clothes_by_category",
            description = "Get the list of existing clothes by categories.",
            returnType = "String")
    public Mono<String> getClothesByCategory() {
        return Mono.just(clothesInventory.toString());
    }
}

