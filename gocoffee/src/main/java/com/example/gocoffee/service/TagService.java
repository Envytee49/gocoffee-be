package com.example.gocoffee.service;

import com.example.gocoffee.dto.request.purpose.CreatePurposeRequest;
import com.example.gocoffee.dto.request.purpose.UpdatePurposeRequest;
import com.example.gocoffee.dto.request.tag.CreateTagRequest;
import com.example.gocoffee.dto.request.tag.UpdateTagRequest;
import com.example.gocoffee.dto.response.purpose.PurposeResponse;

import java.util.List;

public interface TagService {
    void createTag(CreateTagRequest request);
    void updateTag(UpdateTagRequest request);
    void deleteTag(Long id);

    List<PurposeResponse> getAllTags();

    PurposeResponse getTagById(Long id);
}
