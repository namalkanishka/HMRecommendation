package domain.product.mapper;

import org.example.domain.product.dto.ProductImageDto;
import org.example.domain.product.entity.ProductImage;
import org.example.domain.product.mapper.ProductImageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductImageMapperTest {

  private ProductImageMapper productImageMapper;

  private static final Long IMAGE_ID = 1L;
  private static final String IMAGE_URI = "http://example.com/image.jpg";

  @BeforeEach
  void setUp() {
    productImageMapper = new ProductImageMapper();
  }

  @Test
  void testMapToDto() {
    ProductImage productImage = new ProductImage(IMAGE_ID, IMAGE_URI);

    ProductImageDto productImageDto = productImageMapper.mapToDto(productImage);

    assertEquals(IMAGE_ID, productImageDto.imageId());
    assertEquals(IMAGE_URI, productImageDto.url());
  }

  @Test
  void testMapToEntity() {
    ProductImageDto productImageDto = new ProductImageDto(IMAGE_ID, IMAGE_URI);

    ProductImage productImage = productImageMapper.mapToEntity(productImageDto);

    assertEquals(IMAGE_ID, productImage.getImageId());
    assertEquals(IMAGE_URI, productImage.getImageUri());
  }
}