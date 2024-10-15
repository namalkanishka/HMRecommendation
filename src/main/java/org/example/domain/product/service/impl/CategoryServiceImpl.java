package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.product.dto.CategoryDto;
import org.example.domain.product.mapper.CategoryMapper;
import org.example.domain.product.repository.CategoryRepository;
import org.example.domain.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of CategoryService
 *
 * @author namal
 */
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final CategoryMapper categoryMapper;

  @Override
  public CategoryDto createCategory(CategoryDto categoryDto) {
    return categoryMapper.mapToDto(categoryRepository.save(categoryMapper.mapToEntity(categoryDto)));
  }

  @Override
  public Optional<CategoryDto> updateCategory(Long id, CategoryDto categoryDto) {
    return categoryRepository.findById(id).map(category -> {
      category.setCategory(categoryDto.name());
      categoryRepository.save(category);
      return categoryMapper.mapToDto(category);
    });

  }

  @Override
  public boolean deleteCategory(Long id) {
    if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Optional<CategoryDto> getCategoryById(Long id) {
    return categoryRepository.findById(id).map(categoryMapper::mapToDto);
  }

  @Override
  public List<CategoryDto> getAllCategories() {
    return categoryRepository.findAll().stream().map(categoryMapper::mapToDto).toList();
  }
}
