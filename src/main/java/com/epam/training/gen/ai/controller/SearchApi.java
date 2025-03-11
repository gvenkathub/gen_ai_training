package com.epam.training.gen.ai.controller;

import com.epam.training.gen.ai.history.SimpleKernelHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SearchApi {

    @Autowired
    private SimpleKernelHistory simpleKernelHistory;


    @PostMapping("/search")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String response = simpleKernelHistory.processWithHistory(message);
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("response", response);
        return jsonResponse;
    }
}