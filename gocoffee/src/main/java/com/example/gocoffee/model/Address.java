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
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String province;
    private String district;
    private String ward;
    private String addressDetails;
    private Double longitude;
    private Double latitude;
    @Enumerated(EnumType.ORDINAL)
    private EntityStatus status;
}
