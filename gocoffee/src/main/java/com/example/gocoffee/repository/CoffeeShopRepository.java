package com.example.gocoffee.repository;

import com.example.gocoffee.enums.ImageTypeEnum;
import com.example.gocoffee.model.CoffeeShop;
import com.example.gocoffee.repository.projection.coffeeshop.CoffeeShopProjection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoffeeShopRepository extends JpaRepository<CoffeeShop, Long> {
    @Query(value =
            """
                    SELECT
                    cf.id AS coffeeShopId,
                    cf.name as name,
                    a.addressDetails as detailAddress,
                    i.path as coverImageLink
                    FROM CoffeeShop cf
                    JOIN Image i ON i.coffeeShop.id = cf.id AND i.imageType = :imageType
                    JOIN Address a ON a.id = cf.address.id
                    """)
    List<CoffeeShopProjection> getCoffeeShop(ImageTypeEnum imageType, PageRequest pageRequest);
}
