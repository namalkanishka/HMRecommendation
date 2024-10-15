package org.example.domain.recommendation.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for preference
 *
 * @author namal
 */
public record PreferenceDto(
        @NotNull Long categoryId,
        @NotNull Long eventId,
        @NotNull Double minBudget,
        @NotNull Double maxBudget,
        List<Long> tagsIds
) {
}
