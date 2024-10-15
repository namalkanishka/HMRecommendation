package domain.product.mapper;

import org.example.domain.product.dto.ProductTagDto;
import org.example.domain.product.entity.ProductTag;
import org.example.domain.product.mapper.ProductTagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductTagMapperTest {

  private ProductTagMapper productTagMapper;

  private static final Long TAG_ID = 1L;
  private static final String TAG_NAME = "Test Tag";

  @BeforeEach
  void setUp() {
    productTagMapper = new ProductTagMapper();
  }

  @Test
  void testMapToDto() {
    ProductTag productTag = new ProductTag(TAG_ID, TAG_NAME);

    ProductTagDto productTagDto = productTagMapper.mapToDto(productTag);

    assertEquals(TAG_ID, productTagDto.tagId());
    assertEquals(TAG_NAME, productTagDto.tag());
  }

  @Test
  void testMapToEntity() {
    ProductTagDto productTagDto = new ProductTagDto(TAG_ID, TAG_NAME);

    ProductTag productTag = productTagMapper.mapToEntity(productTagDto);

    assertEquals(TAG_ID, productTag.getTagId());
    assertEquals(TAG_NAME, productTag.getTag());
  }
}
