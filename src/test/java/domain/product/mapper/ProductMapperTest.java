package domain.product.mapper;

import org.example.domain.inventory.entity.Inventory;
import org.example.domain.product.dto.ProductDetailDto;
import org.example.domain.product.entity.Category;
import org.example.domain.product.entity.Event;
import org.example.domain.product.entity.Product;
import org.example.domain.product.mapper.*;
import org.example.domain.inventory.mapper.InventoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

  private ProductMapper productMapper;

  @Mock
  private CategoryMapper categoryMapper;

  @Mock
  private ProductTagMapper productTagMapper;

  @Mock
  private ProductImageMapper productImageMapper;

  @Mock
  private EventMapper eventMapper;

  @Mock
  private InventoryMapper inventoryMapper;

  private static final Long PRODUCT_ID = 1L;
    private static final String NAME = "Test Product";
    private static final String DESCRIPTION = "Test Description";
    private static final double DEFAULT_PRICE = 99.99;
    private static final double MIN_PRICE = 49.99;


  @BeforeEach
  void setUp() {
    productMapper = new ProductMapper(categoryMapper, productTagMapper, productImageMapper, eventMapper, inventoryMapper);
  }

  @Test
  void testMapToDto() {
    Product product = Product.builder()
            .productId(PRODUCT_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .defaultPrice(DEFAULT_PRICE)
            .minPrice(MIN_PRICE)
            .productTags(Collections.emptyList())
            .productImages(Collections.emptyList())
            .build();

    when(eventMapper.mapToDto(product.getEvent())).thenReturn(null);
    when(categoryMapper.mapToDto(product.getCategory())).thenReturn(null);
    when(inventoryMapper.mapToDto(product.getInventory())).thenReturn(null);

    ProductDetailDto productDetailDto = productMapper.mapToDto(product);

    assertEquals(PRODUCT_ID, productDetailDto.productId());
    assertEquals(NAME, productDetailDto.name());
    assertEquals(DESCRIPTION, productDetailDto.description());
    assertEquals(DEFAULT_PRICE, productDetailDto.defaultPrice());
    assertEquals(MIN_PRICE, productDetailDto.minPrice());

    verify(eventMapper, times(1)).mapToDto(product.getEvent());
    verify(categoryMapper, times(1)).mapToDto(product.getCategory());
    verify(inventoryMapper, times(1)).mapToDto(product.getInventory());
  }

  @Test
  void testMapToEntity() {
    ProductDetailDto productDetailDto = ProductDetailDto.builder()
            .productId(PRODUCT_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .defaultPrice(DEFAULT_PRICE)
            .minPrice(MIN_PRICE)
            .productTags(Collections.emptyList())
            .productImages(Collections.emptyList())
            .build();

    when(eventMapper.mapToEntity(productDetailDto.event())).thenReturn(new Event());
    when(categoryMapper.mapToEntity(productDetailDto.category())).thenReturn(new Category());
    when(inventoryMapper.mapToEntity(productDetailDto.inventory())).thenReturn(new Inventory());

    Product product = productMapper.mapToEntity(productDetailDto);

    assertEquals(PRODUCT_ID, product.getProductId());
    assertEquals(NAME, product.getName());
    assertEquals(DESCRIPTION, product.getDescription());
    assertEquals(DEFAULT_PRICE, product.getDefaultPrice());
    assertEquals(MIN_PRICE, product.getMinPrice());

    verify(eventMapper, times(1)).mapToEntity(productDetailDto.event());
    verify(categoryMapper, times(1)).mapToEntity(productDetailDto.category());
    verify(inventoryMapper, times(1)).mapToEntity(productDetailDto.inventory());
  }
}