package com.productservice.dtos;

import java.util.UUID;

public record ProductRequestSelfDto(
        String title,
        String description,
        double price,
        String imagePath,
        int quantity,
        UUID category) {
}
