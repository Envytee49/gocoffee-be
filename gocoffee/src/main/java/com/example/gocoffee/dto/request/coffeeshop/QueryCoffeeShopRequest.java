package com.example.gocoffee.dto.request.coffeeshop;

import java.util.List;

public record QueryCoffeeShopRequest(
        String longitude,
        String latitude,
        String name,
        Double minPrice,
        Double maxPrice,
        List<Integer> purposeIds,
        Integer distance,
        Boolean isOvernight,
        Boolean isOpening,
        List<Long> tagIds
) {

}
