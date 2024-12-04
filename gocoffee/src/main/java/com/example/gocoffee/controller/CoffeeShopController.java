package com.example.gocoffee.controller;

import com.example.gocoffee.dto.request.PageDtoRequest;
import com.example.gocoffee.dto.request.coffeeshop.QueryCoffeeShopRequest;
import com.example.gocoffee.dto.request.coffeeshop.TagShopRequest;
import com.example.gocoffee.dto.response.ApiResponse;
import com.example.gocoffee.dto.response.coffeeshop.DetailCoffeeShopResponse;
import com.example.gocoffee.model.CoffeeShop;
import com.example.gocoffee.service.CoffeeShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee-shops")
@Validated
@Slf4j
public class CoffeeShopController extends AbstractController {
    private final CoffeeShopService coffeeShopService;

    @GetMapping
    public ApiResponse<?> getCoffeeShopByFilters(@Valid PageDtoRequest pageDtoRequest,
                                                 QueryCoffeeShopRequest queryRequest) {
        return respond(() -> coffeeShopService.getCoffeeShopByFilter(pageDtoRequest, queryRequest));
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getCoffeeShop(@PathVariable long id) {
        return respond(() -> coffeeShopService.getCoffeeById(id));
    }

    @PutMapping("/tag")
    public ApiResponse<?> tagCoffeeShop(@RequestBody @Valid TagShopRequest request) {
        return respond(() -> coffeeShopService.tagShop(request));
    }
}

