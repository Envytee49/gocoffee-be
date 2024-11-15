package com.example.gocoffee.service.impl;

import com.example.gocoffee.enums.SettingEnum;
import com.example.gocoffee.model.Setting;
import com.example.gocoffee.repository.SettingRepository;
import com.example.gocoffee.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
    private final SettingRepository settingRepository;
    @Override
    public void createTagMaxLimitSetting(final int tagMaxLimit) {
        settingRepository.save(Setting.builder()
                .key(SettingEnum.TAG_MAX_LIMIT.name())
                .value(String.valueOf(tagMaxLimit))
                .build());
    }

    @Override
    public int getTagMaxLimit() {
        Optional<Setting> setting = settingRepository.findById(SettingEnum.TAG_MAX_LIMIT.name());
        return setting.map(value -> Integer.parseInt(value.getValue())).orElse(3);
    }

}
