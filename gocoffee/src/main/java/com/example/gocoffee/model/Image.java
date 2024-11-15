package com.example.gocoffee.model;

import com.example.gocoffee.enums.EntityStatus;
import com.example.gocoffee.enums.ImageTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_shop_id")
    @JsonBackReference
    private CoffeeShop coffeeShop;
    private String path;
    @Enumerated(EnumType.ORDINAL)
    private ImageTypeEnum imageType;
    @Enumerated(EnumType.ORDINAL)
    private EntityStatus status;

}
