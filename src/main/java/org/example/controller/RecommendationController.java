package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.domain.recommendation.dto.PreferenceDto;
import org.example.domain.recommendation.dto.RecommendationDto;
import org.example.domain.recommendation.service.RecommendationService;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for recommendation related operations
 *
 * @author namal
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/recommendations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {

  private final RecommendationService recommendationService;

  /**
   * Get recommendations based on preference
   *
   * @param preferenceDto preference details
   * @param page          page number
   * @param size          page size
   * @return list of recommendations
   */
  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PagedModel<RecommendationDto>> getRecommendations(@Valid @RequestBody PreferenceDto preferenceDto,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size
  ) {

    return ResponseEntity.ok(recommendationService.getRecommendations(
            preferenceDto,
            Pageable.ofSize(size).withPage(page)));
  }
}
