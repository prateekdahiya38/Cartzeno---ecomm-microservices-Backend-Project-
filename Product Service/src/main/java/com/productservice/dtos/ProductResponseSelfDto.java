package com.productservice.dtos;

import java.util.UUID;

public record ProductResponseSelfDto(
        UUID id,
        String title,
        String description,
        double price,
        int quantity,
        String imagePath,
        String category) {
}
