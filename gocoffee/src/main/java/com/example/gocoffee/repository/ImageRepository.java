package com.example.gocoffee.repository;

import com.example.gocoffee.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
