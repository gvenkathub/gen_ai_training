package com.epam.training.gen.ai.dto;

public record ErrorResponse (
    int status,
    String message
) {}