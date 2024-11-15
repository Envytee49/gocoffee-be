package com.example.gocoffee.model.compositeid;

import com.example.gocoffee.model.CoffeeShop;
import com.example.gocoffee.model.Tag;
import lombok.Data;

@Data
public class CoffeeShopTagId {
    private CoffeeShop coffeeShop;
    private Tag tag;
}
