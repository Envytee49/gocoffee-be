package com.example.gocoffee.dto.response.coffeeshop;

import lombok.Builder;

@Builder
public record QueryCoffeeShopResponse(long coffeeShopId,
                                      String name,
                                      String detailAddress,
                                      String coverImageLink) {

}
