package org.example.domain.recommendation.dto;

import lombok.Builder;

import java.util.List;

/**
 * DTO for recommendation
 *
 * @author namal
 */
@Builder
public record RecommendationDto(
        Long productId,
        String name,
        Double defaultPrice,
        List<String> images
) {
}
