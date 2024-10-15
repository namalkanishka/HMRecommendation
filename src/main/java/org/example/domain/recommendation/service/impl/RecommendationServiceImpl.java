package org.example.domain.recommendation.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.product.dto.ProductDto;
import org.example.domain.product.dto.ProductImageDto;
import org.example.domain.product.service.ProductService;
import org.example.domain.recommendation.dto.PreferenceDto;
import org.example.domain.recommendation.dto.RecommendationDto;
import org.example.domain.recommendation.service.RecommendationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendationServiceImpl implements RecommendationService {

  private final ProductService productService;

  @Override
  public PagedModel<RecommendationDto> getRecommendations(PreferenceDto preferenceDto, Pageable pageable) {
    Page<ProductDto> productDtoList = productService.getProductsByPreference(preferenceDto, pageable);

    PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
            productDtoList.getSize(),
            productDtoList.getNumber(),
            productDtoList.getTotalElements(),
            productDtoList.getTotalPages()
    );

    List<RecommendationDto> recommendationDtoList = productDtoList.getContent().stream()
            .map(product -> new RecommendationDto(
                    product.productId(),
                    product.name(),
                    product.defaultPrice(),
                    product.productImages().stream().map(ProductImageDto::url).toList())).toList();

    return PagedModel.of(recommendationDtoList, metadata);



  }
}
