package org.example.domain.recommendation.service;

import org.example.domain.recommendation.dto.PreferenceDto;
import org.example.domain.recommendation.dto.RecommendationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

/**
 * Service for recommendation related operations
 *
 * @author namal
 */
public interface RecommendationService {

  /**
   * Get recommendations based on the preference
   *
   * @param preferenceDto preference details
   * @param pageable      pagination details
   * @return {@link PagedModel<RecommendationDto>} list of recommendations
   */
  PagedModel<RecommendationDto> getRecommendations(PreferenceDto preferenceDto, Pageable pageable);

}
