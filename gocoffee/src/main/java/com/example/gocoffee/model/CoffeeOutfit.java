package com.example.gocoffee.model;

import com.example.gocoffee.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coffee_outfits")
public class CoffeeOutfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long coffeeShopId;
    @OneToOne
    private User user;
    @OneToOne
    private Image image;
    private String jsonOutfit;
    @Enumerated(EnumType.ORDINAL)
    private EntityStatus status;
}
