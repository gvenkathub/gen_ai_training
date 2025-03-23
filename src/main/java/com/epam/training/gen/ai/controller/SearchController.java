package com.epam.training.gen.ai.controller;


import com.epam.training.gen.ai.service.HistoryPromptService;
import com.epam.training.gen.ai.service.PromptService;
import com.microsoft.semantickernel.Kernel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
public class SearchController {
    final PromptService promptService;

    public SearchController(PromptService promptService, HistoryPromptService historyPromptService, Kernel kernel) {
        this.promptService = promptService;
    }

    @GetMapping("/search")
    public List<String> chat(@RequestParam("input") String input){
        return promptService.getChatCompletions(input).stream().toList();
    }
}

