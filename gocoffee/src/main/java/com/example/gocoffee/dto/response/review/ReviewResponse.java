package com.example.gocoffee.dto.response.review;

import java.util.List;

public record ReviewResponse(
        Double averageRating,
        Integer ratingCounts,
        List<DetailReviewResponse> detailReviews
) {
}
