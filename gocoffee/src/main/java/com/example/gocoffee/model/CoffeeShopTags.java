package com.example.gocoffee.model;

import com.example.gocoffee.model.compositeid.CoffeeShopTagId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coffee_shop_tags")
@IdClass(CoffeeShopTagId.class)
public class CoffeeShopTags {
    @Id
    @ManyToOne
    @JoinColumn(name = "coffee_shop_id")
    @JsonBackReference
    private CoffeeShop coffeeShop;
    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    @JsonBackReference
    private Tag tag;
}
