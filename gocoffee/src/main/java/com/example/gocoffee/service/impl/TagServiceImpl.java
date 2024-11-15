package com.example.gocoffee.service.impl;

import com.example.gocoffee.dto.request.tag.CreateTagRequest;
import com.example.gocoffee.dto.request.tag.UpdateTagRequest;
import com.example.gocoffee.dto.response.purpose.PurposeResponse;
import com.example.gocoffee.exception.AppException;
import com.example.gocoffee.exception.ErrorCode;
import com.example.gocoffee.model.Tag;
import com.example.gocoffee.repository.TagRepository;
import com.example.gocoffee.service.TagService;
import com.example.gocoffee.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public void createTag(final CreateTagRequest request) {
        if(tagRepository.existsByName(request.name()))
            throw new AppException(ErrorCode.NAME_EXISTED);
        Tag tag = Tag
                .builder()
                .name(request.name())
                .count(0L)
                .build();
        tagRepository.save(tag);
    }

    @Override
    public void updateTag(final UpdateTagRequest request) {

    }

    @Override
    public void deleteTag(final Long id) {

    }

    @Override
    public List<PurposeResponse> getAllTags() {
        return List.of();
    }

    @Override
    public PurposeResponse getTagById(final Long id) {
        return null;
    }
}
