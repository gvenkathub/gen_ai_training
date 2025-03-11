package com.epam.training.gen.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Gen DEV AI Training Application!";
    }
}