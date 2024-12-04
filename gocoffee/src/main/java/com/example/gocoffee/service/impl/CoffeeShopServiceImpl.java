package com.example.gocoffee.service.impl;

import com.example.gocoffee.dto.request.PageDtoRequest;
import com.example.gocoffee.dto.request.coffeeshop.CreateCoffeeShopRequest;
import com.example.gocoffee.dto.request.coffeeshop.QueryCoffeeShopRequest;
import com.example.gocoffee.dto.request.coffeeshop.TagShopRequest;
import com.example.gocoffee.dto.request.coffeeshop.UpdateCoffeeShopRequest;
import com.example.gocoffee.dto.response.PageDtoResponse;
import com.example.gocoffee.dto.response.coffeeshop.DetailCoffeeShopResponse;
import com.example.gocoffee.dto.response.coffeeshop.QueryCoffeeShopResponse;
import com.example.gocoffee.enums.ImageTypeEnum;
import com.example.gocoffee.exception.AppException;
import com.example.gocoffee.exception.ErrorCode;
import com.example.gocoffee.model.Address;
import com.example.gocoffee.model.CoffeeShop;
import com.example.gocoffee.model.CoffeeShopTags;
import com.example.gocoffee.model.Tag;
import com.example.gocoffee.repository.CoffeeShopRepository;
import com.example.gocoffee.repository.TagRepository;
import com.example.gocoffee.repository.projection.coffeeshop.CoffeeShopProjection;
import com.example.gocoffee.service.CoffeeShopService;
import com.example.gocoffee.service.SettingService;
import com.example.gocoffee.util.AppStringUtil;
import com.example.gocoffee.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeShopServiceImpl implements CoffeeShopService {
    private final CoffeeShopRepository coffeeShopRepository;
    private final TagRepository tagRepository;
    private final SettingService settingService;
    private final ObjectMapper objectMapper;

    @Override
    public PageDtoResponse<QueryCoffeeShopResponse> getCoffeeShopByFilter(final PageDtoRequest pageDtoRequest,
                                                                          final QueryCoffeeShopRequest queryRequest) {
        PageRequest pageRequest = PageRequest.of(pageDtoRequest.getPage(), pageDtoRequest.getSize());
        Page<CoffeeShopProjection> coffeeShopProjections = coffeeShopRepository.getCoffeeShop(
                ImageTypeEnum.COVER,
                pageRequest);
        List<QueryCoffeeShopResponse> queryCoffeeShopResponses = coffeeShopProjections.getContent().stream()
                .map(shop -> QueryCoffeeShopResponse.builder()
                        .coffeeShopId(shop.getCoffeeShopId())
                        .name(shop.getName())
                        .coverImageLink(shop.getCoverImageLink())
                        .detailAddress(shop.getDetailAddress())
                        .build())
                .toList();
        return PageDtoResponse.from(
                pageDtoRequest.getPage(),
                pageDtoRequest.getSize(),
                coffeeShopProjections.getTotalElements(),
                queryCoffeeShopResponses
        );
    }

    @Override
    public DetailCoffeeShopResponse getCoffeeById(final Long id) {
        CoffeeShop coffeeShop = coffeeShopRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        int openTimeInSeconds = coffeeShop.getOpenTimeInSeconds();
        int closeTimeInSeconds = coffeeShop.getCloseTimeInSeconds();
        String openTimeOfDay = DateUtil.getTimeOfDay(openTimeInSeconds);
        String closeTimeOfDay = DateUtil.getTimeOfDay(closeTimeInSeconds);
        Address address = coffeeShop.getAddress();
        String detailAddress = AppStringUtil.concatString(
                address.getProvince(),
                address.getDistrict(),
                address.getWard(),
                address.getAddressDetails());
        boolean isOpening = DateUtil.isTimeNowInTimeRange(openTimeInSeconds, closeTimeInSeconds);
        List<DetailCoffeeShopResponse.ImageResponse> images = coffeeShop.getImages().stream()
                .map(image -> objectMapper.convertValue(image, DetailCoffeeShopResponse.ImageResponse.class))
                .toList();
        List<DetailCoffeeShopResponse.TagResponse> tags = coffeeShop.getTags().stream()
                .map(tag -> objectMapper.convertValue(tag.getTag(), DetailCoffeeShopResponse.TagResponse.class))
                .toList();
        return DetailCoffeeShopResponse.builder()
                .name(coffeeShop.getName())
                .openTime(openTimeOfDay)
                .closeTime(closeTimeOfDay)
                .detailAddress(detailAddress)
                .isOpening(isOpening)
                .hotline(coffeeShop.getHotline())
                .averagePrice(coffeeShop.getAveragePrice())
                .parking(coffeeShop.getParking())
                .description(coffeeShop.getDescription())
                .images(images)
                .tags(tags)
                .build();
    }

    @Override
    public void createCoffeeShop(final CreateCoffeeShopRequest request) {

    }

    @Override
    public void deleteCoffeeShop(final Long id) {

    }

    @Override
    public void updateCoffeeShop(final UpdateCoffeeShopRequest request) {

    }

    @Override
    @Transactional
    public void tagShop(final TagShopRequest request) {
        CoffeeShop coffeeShop = coffeeShopRepository.findById(request.coffeeShopId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        Tag tag = tagRepository.findById((request.tagId()))
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        int tagMaxLimit = settingService.getTagMaxLimit();
        int userTagCount = 0;
//        int userTagCount = tagRepository.countUserTagsForCoffeeShop(SecurityUtil.getUserEmail(), coffeeShop.getId());
        if (userTagCount > tagMaxLimit) throw new AppException(ErrorCode.TAG_LIMIT_EXCEEDED);
        coffeeShop.getTags().add(CoffeeShopTags.builder()
                .coffeeShop(coffeeShop)
                .tag(tag)
                .build());
        tag.setCount(tag.getCount() + 1);
        tagRepository.save(tag);
        coffeeShopRepository.save(coffeeShop);
    }
}
