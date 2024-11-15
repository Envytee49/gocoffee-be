package com.example.gocoffee.dto.response.review;

import java.time.LocalDateTime;

public record DetailReviewResponse(
        String imageUrl,
        String userId,
        Integer rating,
        String comment,
        LocalDateTime reviewedAt
) {
}
