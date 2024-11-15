package com.example.gocoffee.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coffee_shop_likes")
public class CoffeeShopLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long coffeeShopId;
    private Long userId;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
