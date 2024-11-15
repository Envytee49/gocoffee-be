package com.example.gocoffee.dto.response.coffeeshop;

import com.example.gocoffee.dto.response.purpose.PurposeResponse;
import com.example.gocoffee.dto.response.tag.TagResponse;
import com.example.gocoffee.enums.ImageTypeEnum;
import com.example.gocoffee.model.Image;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
@Builder
public record DetailCoffeeShopResponse(
        String name,
        String detailAddress,
        boolean isOpening,
        String openTime,
        String closeTime,
        Integer averagePrice,
        String parking,
        String hotline,
        String description,
        List<ImageResponse> images,
        List<PurposeResponse> purposes,
        List<TagResponse> tags
) {
    @Builder
    public record ImageResponse(String path, ImageTypeEnum imageType) {

    }
    @Builder
    public record TagResponse(Long id, String name) {}
}
