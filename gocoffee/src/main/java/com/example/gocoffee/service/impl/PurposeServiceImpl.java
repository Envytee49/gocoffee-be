package com.example.gocoffee.service.impl;

import com.example.gocoffee.dto.request.purpose.CreatePurposeRequest;
import com.example.gocoffee.dto.request.purpose.UpdatePurposeRequest;
import com.example.gocoffee.dto.response.purpose.PurposeResponse;
import com.example.gocoffee.exception.AppException;
import com.example.gocoffee.exception.ErrorCode;
import com.example.gocoffee.model.Purpose;
import com.example.gocoffee.repository.PurposeRepository;
import com.example.gocoffee.service.PurposeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PurposeServiceImpl implements PurposeService {
    private static final Logger log = LoggerFactory.getLogger(PurposeServiceImpl.class);
    private final PurposeRepository purposeRepository;
    @Override
    public void createPurpose(final CreatePurposeRequest request) {
        if(purposeRepository.existsByName(request.name()))
            throw new AppException(ErrorCode.NAME_EXISTED);
        Purpose purpose = Purpose
                .builder()
                .name(request.name())
                .status(request.status())
                .build();
        log.info("id, {}", purpose.getId());
        purposeRepository.save(purpose);
    }

    @Override
    public void updatePurpose(final UpdatePurposeRequest request) {

    }

    @Override
    public void deletePurpose(final Integer id) {

    }

    @Override
    public List<PurposeResponse> getAllPurposes() {
        return List.of();
    }

    @Override
    public PurposeResponse getPurposeById(final Integer id) {
        return null;
    }
}
