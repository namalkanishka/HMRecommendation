package domain.recomatation.service;

import org.example.domain.product.dto.ProductDto;
import org.example.domain.product.dto.ProductImageDto;
import org.example.domain.product.service.ProductService;
import org.example.domain.recommendation.dto.PreferenceDto;
import org.example.domain.recommendation.dto.RecommendationDto;
import org.example.domain.recommendation.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceImplTest {

  private RecommendationServiceImpl recommendationService;

  @Mock
  private ProductService mockProductService;

  private static final Long CATEGORY_ID = 1L;
  private static final Long EVENT_ID = 2L;
  private static final Double MIN_BUDGET = 100.0;
  private static final Double MAX_BUDGET = 200.0;
  private static final Long PRODUCT_ID = 1L;
  private static final String PRODUCT_NAME = "Product 1";
  private static final Double PRODUCT_DEFAULT_PRICE = 100.0;
  private static final String PRODUCT_IMAGE_URL = "http://image.url";

  @BeforeEach
  void setUp() {
    recommendationService = new RecommendationServiceImpl(mockProductService);
  }

  @Test
  void testGetRecommendationsWhenNoTags() {
    PreferenceDto preferenceDto = new PreferenceDto(
            CATEGORY_ID,
            EVENT_ID,
            MIN_BUDGET,
            MAX_BUDGET,
            null
    );
    Pageable pageable = PageRequest.of(0, 10);

    ProductDto productDto = new ProductDto(
            PRODUCT_ID,
            PRODUCT_NAME,
            PRODUCT_DEFAULT_PRICE,
            Collections.singletonList(new ProductImageDto(PRODUCT_ID, PRODUCT_IMAGE_URL)));

    Page<ProductDto> productPage = new PageImpl<>(Collections.singletonList(productDto));
    when(mockProductService.getProductsByPreference(preferenceDto, pageable)).thenReturn(productPage);

    PagedModel<RecommendationDto> recommendations = recommendationService.getRecommendations(preferenceDto, pageable);

    verify(mockProductService, timeout(1)).getProductsByPreference(preferenceDto, pageable);

    assertEquals(1, Objects.requireNonNull(recommendations.getMetadata()).getTotalElements());

    RecommendationDto recommendation = recommendations.getContent().stream().toList().get(0);
    assertEquals(PRODUCT_ID, recommendation.productId());
    assertEquals(PRODUCT_NAME, recommendation.name());
    assertEquals(PRODUCT_DEFAULT_PRICE, recommendation.defaultPrice());
    assertEquals(PRODUCT_IMAGE_URL, recommendation.images().get(0));
  }
}