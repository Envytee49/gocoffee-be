package com.example.gocoffee.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long count;
    @OneToMany(mappedBy = "tag", fetch = FetchType.EAGER)
    private List<CoffeeShopTags> coffeeShops;
}
