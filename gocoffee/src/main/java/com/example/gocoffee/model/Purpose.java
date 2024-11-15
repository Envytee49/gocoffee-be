package com.example.gocoffee.model;

import com.example.gocoffee.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purposes")
public class Purpose extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private EntityStatus status;
    @ManyToMany
    private List<CoffeeShop> coffeeShops;
}
