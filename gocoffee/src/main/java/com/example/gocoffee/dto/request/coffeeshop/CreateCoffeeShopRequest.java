package com.example.gocoffee.dto.request.coffeeshop;

import com.example.gocoffee.dto.response.purpose.PurposeResponse;
import com.example.gocoffee.dto.response.tag.TagResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
public record CreateCoffeeShopRequest(
        String name,
        String province,
        String district,
        String ward,
        String addressDetails,
        String longitude,
        String latitude,
        String detailAddress,
        LocalTime openTime,
        LocalTime closeTime,
        @NotNull
        @Min(1)
        @Max(1)
        MultipartFile coverImage,
        @NotNull
        @Min(3)
        MultipartFile[] overviewImages,
        MultipartFile[] menuImages,
        Double averagePrice,
        String parking,
        String hotline,
        List<PurposeResponse> purposes,
        List<TagResponse> tags,
        Map<String, List<String>> descriptions
) {

}
