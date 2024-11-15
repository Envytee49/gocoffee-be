package com.example.gocoffee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "settings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Setting extends BaseEntity {
    @Id
    private String key;
    private String value;
}
