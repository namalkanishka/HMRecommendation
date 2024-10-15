package domain.product.mapper;

import org.example.domain.product.dto.CategoryDto;
import org.example.domain.product.entity.Category;
import org.example.domain.product.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

  private CategoryMapper categoryMapper;

  private static final Long CATEGORY_ID = 1L;
  private static final String CATEGORY_NAME = "Test Category";

  @BeforeEach
  void setUp() {
    categoryMapper = new CategoryMapper();
  }

  @Test
  void testMapToDto() {
    Category category = new Category(CATEGORY_ID, CATEGORY_NAME);

    CategoryDto categoryDto = categoryMapper.mapToDto(category);

    assertEquals(CATEGORY_ID, categoryDto.categoryId());
    assertEquals(CATEGORY_NAME, categoryDto.name());
  }

  @Test
  void testMapToEntity() {
    CategoryDto categoryDto = new CategoryDto(CATEGORY_ID, CATEGORY_NAME);

    Category category = categoryMapper.mapToEntity(categoryDto);

    assertEquals(CATEGORY_ID, category.getCategoryId());
    assertEquals(CATEGORY_NAME, category.getCategory());
  }
}