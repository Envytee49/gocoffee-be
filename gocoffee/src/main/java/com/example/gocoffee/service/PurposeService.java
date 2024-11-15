package com.example.gocoffee.service;

import com.example.gocoffee.dto.request.purpose.CreatePurposeRequest;
import com.example.gocoffee.dto.request.purpose.UpdatePurposeRequest;
import com.example.gocoffee.dto.response.purpose.PurposeResponse;

import java.util.List;

public interface PurposeService {
    void createPurpose(CreatePurposeRequest request);

    void updatePurpose(UpdatePurposeRequest request);
    void deletePurpose(Integer id);

    List<PurposeResponse> getAllPurposes();

    PurposeResponse getPurposeById(Integer id);
}
