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
@Table(name = "coffee_shop_reviews")
public class CoffeeShopReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long coffeeShopId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String comment;
    private Integer rating;
    private boolean isAnonymous;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
