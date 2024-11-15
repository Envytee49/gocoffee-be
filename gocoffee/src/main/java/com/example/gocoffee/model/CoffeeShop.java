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
@Table(name = "coffee_shops")
public class CoffeeShop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Address address;
    private String hotline;
    // json
    private String description;
    @OneToMany(mappedBy = "coffeeShop", fetch = FetchType.EAGER)
    private List<Image> images;
    private int openTimeInSeconds;
    private int closeTimeInSeconds;
    // json
    private String socialLink;
    private boolean isOvernight;
    private boolean isSponsor;
    @OneToMany(mappedBy = "coffeeShop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CoffeeShopTags> tags;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "coffee_go_purposes",
            joinColumns = @JoinColumn(
                    name = "coffee_shop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "purpose_id", referencedColumnName = "id")
    )
    private List<Purpose> purposes;
    private String parking;
    private Integer averagePrice;
    @Enumerated(EnumType.ORDINAL)
    private EntityStatus status;
}


