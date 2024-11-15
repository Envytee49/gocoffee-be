package com.example.gocoffee.repository;

import com.example.gocoffee.model.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurposeRepository extends JpaRepository<Purpose, Integer> {
    boolean existsByName(String name);
}
