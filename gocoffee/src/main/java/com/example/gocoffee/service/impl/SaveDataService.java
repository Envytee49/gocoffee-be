package com.example.gocoffee.service.impl;

import com.example.gocoffee.enums.EntityStatus;
import com.example.gocoffee.enums.ImageTypeEnum;
import com.example.gocoffee.model.Address;
import com.example.gocoffee.model.CoffeeShop;
import com.example.gocoffee.model.Image;
import com.example.gocoffee.repository.AddressRepository;
import com.example.gocoffee.repository.CoffeeShopRepository;
import com.example.gocoffee.repository.ImageRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class SaveDataService {
    private final CoffeeShopRepository coffeeShopRepository;
    private final AddressRepository addressRepository;
    private final ImageRepository imageRepository;

    @Data
    static class CoffeeMapper {
        private String name;
        private boolean isPublic;
        private String descriptions;
        private String coverImage;
        private String parking;
        private int openTimeBySeconds;
        private int closeTimeBySeconds;
        private String address;
        private String ward;
        private String district;
        private String city;
        private int priceAverage;
        private int priceMin;
        private int priceMax;
        private String slug;
        private double latitude;
        private double longitude;
        @JsonProperty("is24h")
        private boolean is24h;
        @JsonProperty("isSponsor")
        private boolean isSponsor;
        private String wifiPassword;
        private String googlePlaceId;
        @JsonProperty("isDraft")
        private boolean isDraft;
        private String contributorId;
        private List<Media> medias;
    }

    @Data
    static class Media {
        private String id;
        private String url;
        private String type;
        private String createdAt;
        private String brandId;
    }

    @Transactional
    public void saveData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CoffeeMapper> coffeeList = null;
        try {
            // Read JSON from file
            File file = new File(getClass().getClassLoader().getResource("coffee.json").getFile());
            coffeeList = objectMapper.readValue(file, new TypeReference<List<CoffeeMapper>>() {});

            // Select specific fields
            for (CoffeeMapper coffeeMapper : coffeeList) {
                log.info("saving , {}", coffeeMapper.getName());
                Address address = Address.builder()
                        .addressDetails(coffeeMapper.getAddress())
                        .longitude(coffeeMapper.getLongitude())
                        .latitude(coffeeMapper.getLatitude())
                        .district(coffeeMapper.getDistrict())
                        .ward(coffeeMapper.getWard())
                        .province(coffeeMapper.getCity())
                        .status(EntityStatus.ACTIVE)
                        .build();
                addressRepository.save(address);

                CoffeeShop coffeeShop = CoffeeShop.builder()
                        .address(address)
                        .name(coffeeMapper.getName())
                        .description(coffeeMapper.getDescriptions())
                        .isOvernight(coffeeMapper.is24h)
                        .isSponsor(coffeeMapper.isSponsor)
                        .openTimeInSeconds(coffeeMapper.openTimeBySeconds)
                        .closeTimeInSeconds(coffeeMapper.closeTimeBySeconds)
                        .parking(coffeeMapper.getParking())
                        .averagePrice(coffeeMapper.getPriceAverage())
                        .status(EntityStatus.ACTIVE)
                        .build();
                List<Media> medias = coffeeMapper.getMedias();
                List<Image> images = new ArrayList<>();
                CoffeeShop savedCoffee = coffeeShopRepository.save(coffeeShop);
                images.add(Image.builder()
                        .coffeeShop(savedCoffee)
                        .path(coffeeMapper.getCoverImage())
                        .imageType(ImageTypeEnum.COVER)
                        .status(EntityStatus.ACTIVE)
                        .build());
                for (Media media : medias) {
                    images.add(Image.builder()
                            .coffeeShop(savedCoffee)
                            .path(media.getUrl())
                            .imageType(ImageTypeEnum.OVERVIEW)
                            .status(EntityStatus.ACTIVE)
                            .build());
                }
                imageRepository.saveAll(images);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
