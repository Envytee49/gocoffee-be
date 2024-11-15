package com.example.gocoffee.repository;

import com.example.gocoffee.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
    @Query(value = """
        SELECT COUNT(*) FROM Tag t
        JOIN CoffeeShopTags cst ON cst.tag.id = t.id AND cst.coffeeShop.id = :shopId
        WHERE t.updatedBy = :updatedBy
            """)
    int countUserTagsForCoffeeShop(String updatedBy, Long shopId);
}
