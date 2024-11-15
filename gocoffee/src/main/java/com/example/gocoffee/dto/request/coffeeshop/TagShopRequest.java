package com.example.gocoffee.dto.request.coffeeshop;

import jakarta.validation.constraints.NotNull;

public record TagShopRequest(@NotNull(message = "tagId must not be null") Long tagId,
                             @NotNull(message = "coffeeShopId must not be null") Long coffeeShopId) {
}
