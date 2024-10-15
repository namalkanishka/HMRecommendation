package domain.product.service;

import org.example.domain.product.dto.ProductDto;
import org.example.domain.product.entity.Product;
import org.example.domain.product.mapper.ProductImageMapper;
import org.example.domain.product.mapper.ProductMapper;
import org.example.domain.product.mapper.ProductTagMapper;
import org.example.domain.product.repository.ProductImageRepository;
import org.example.domain.product.repository.ProductRepository;
import org.example.domain.product.service.CategoryService;
import org.example.domain.product.service.EventService;
import org.example.domain.product.service.ProductService;
import org.example.domain.product.service.ProductTagService;
import org.example.domain.product.service.impl.ProductServiceImpl;
import org.example.domain.recommendation.dto.PreferenceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

  @Mock
  private ProductRepository mockProductRepository;

  @Mock
  private ProductMapper mockProductMapper;

  @Mock
  private CategoryService mockCategoryService;

  @Mock
  private ProductTagService mockProductTagService;

  @Mock
  private EventService mockEventService;

  @Mock
  private ProductImageRepository mockProductImageRepository;

  @Mock
  private ProductImageMapper productImageMapper;

  @Mock
  private ProductTagMapper mockProductTagMapper;

  private ProductService productService;

  private final static Long PRODUCT_ID = 1L;
  private final static Long CATEGORY_ID = 1L;
  private final static Long EVENT_ID = 1L;
  private final static Long PRODUCT_TAG_ID = 1L;
  private final static Long PRODUCT_IMAGE_ID = 1L;
  private final static Long INVENTORY_ID = 1L;
  private final static Double MIN_PRICE = 100.0;
  private final static Double DEFAULT_PRICE = 200.0;
  private final static String NAME = "Test Product";
  private final static String DESCRIPTION = "Test Description";
  private final static Double MIN_BUDGET = 100.0;
  private final static Double MAX_BUDGET = 250.0;

  @BeforeEach
  public void setup() {
    productService = new ProductServiceImpl(
            mockProductRepository,
            mockProductMapper,
            mockCategoryService,
            mockProductTagService,
            mockEventService,
            mockProductImageRepository,
            productImageMapper,
            mockProductTagMapper
    );
  }

  @Test
  public void testGetProductsByPreferenceWithEmptyTags() {
    PreferenceDto preferenceDto = new PreferenceDto(
            CATEGORY_ID, EVENT_ID, MIN_BUDGET, MAX_BUDGET, Collections.emptyList()
    );

    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> productPage = new PageImpl<>(Collections.singletonList(getProduct()));

    when(mockProductRepository.findProductsByCategoryEventAndBudget(
            CATEGORY_ID, EVENT_ID, MIN_BUDGET, MAX_BUDGET, pageable
    )).thenReturn(productPage);

    when(mockProductRepository.findProductsByCategoryEventAndBudget(
            CATEGORY_ID, EVENT_ID, MIN_BUDGET, MAX_BUDGET, pageable
    )).thenReturn(productPage);

    Page<ProductDto> result = productService.getProductsByPreference(preferenceDto, pageable);

    assertEquals(1, result.getTotalElements());
    assertEquals(NAME, result.getContent().get(0).name());
    verify(mockProductRepository, times(1)).findProductsByCategoryEventAndBudget(
            CATEGORY_ID, EVENT_ID, MIN_BUDGET, MAX_BUDGET, pageable
    );
    verify(productImageMapper, times(0)).mapToDto(any());
  }

  @Test
  public void testGetProductsByPreferenceWithTags() {
    List<Long> productTagIds = Collections.singletonList(PRODUCT_TAG_ID);
    PreferenceDto preferenceDto = new PreferenceDto(
            CATEGORY_ID, EVENT_ID, MIN_BUDGET, MAX_BUDGET, productTagIds
    );

    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> productPage = new PageImpl<>(Collections.singletonList(getProduct()));

    when(mockProductRepository.findProductsByCategoryEventBudgetAndTags(
            CATEGORY_ID, EVENT_ID, MIN_BUDGET, MAX_BUDGET, productTagIds ,pageable
    )).thenReturn(productPage);

    Page<ProductDto> result = productService.getProductsByPreference(preferenceDto, pageable);

    assertEquals(1, result.getTotalElements());
    assertEquals(NAME, result.getContent().get(0).name());
    verify(mockProductRepository, times(1)).findProductsByCategoryEventBudgetAndTags(
            CATEGORY_ID, EVENT_ID, MIN_BUDGET, MAX_BUDGET, productTagIds ,pageable
    );
    verify(productImageMapper, times(0)).mapToDto(any());
  }

  private Product getProduct() {
    return Product.builder()
            .productId(PRODUCT_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .minPrice(MIN_PRICE)
            .defaultPrice(DEFAULT_PRICE)
            .productImages(Collections.emptyList())
            .build();
  }
}