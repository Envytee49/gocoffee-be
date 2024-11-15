package com.example.gocoffee.service;

import com.example.gocoffee.dto.request.coffeeshop.CreateCoffeeShopRequest;
import com.example.gocoffee.dto.request.coffeeshop.QueryCoffeeShopRequest;
import com.example.gocoffee.dto.request.coffeeshop.TagShopRequest;
import com.example.gocoffee.dto.request.coffeeshop.UpdateCoffeeShopRequest;
import com.example.gocoffee.dto.response.coffeeshop.DetailCoffeeShopResponse;
import com.example.gocoffee.dto.response.coffeeshop.QueryCoffeeShopResponse;

import java.util.List;

public interface CoffeeShopService {
    List<QueryCoffeeShopResponse> getCoffeeShopByFilter(QueryCoffeeShopRequest request);

    DetailCoffeeShopResponse getCoffeeById(Long id);

    void createCoffeeShop(CreateCoffeeShopRequest request);

    void deleteCoffeeShop(Long id);

    void updateCoffeeShop(UpdateCoffeeShopRequest request);

    void tagShop(TagShopRequest request);

}
