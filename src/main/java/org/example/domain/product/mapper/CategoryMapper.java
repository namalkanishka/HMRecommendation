package org.example.domain.product.mapper;

import org.example.common.Mapper;
import org.example.domain.product.dto.CategoryDto;
import org.example.domain.product.entity.Category;
import org.springframework.stereotype.Component;

/**
 * Mapper for Category entity and CategoryDto
 *
 * @author namal
 */
@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

  @Override
  public CategoryDto mapToDto(Category category) {
    return new CategoryDto(category.getCategoryId(), category.getCategory());
  }

  @Override
  public Category mapToEntity(CategoryDto categoryDto) {
    return new Category(categoryDto.categoryId(), categoryDto.name());
  }
}
