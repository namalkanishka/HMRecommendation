package org.example.domain.product.service;

import org.example.domain.product.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Category.
 *
 * @author namal
 */
public interface CategoryService {

  /**
   * Create a new categoryId
   *
   * @param categoryDto categoryId details
   * @return created categoryId details
   */
  CategoryDto createCategory(CategoryDto categoryDto);

  /**
   * Update a categoryId
   *
   * @param id          categoryId id
   * @param categoryDto categoryId details
   * @return updated categoryId details
   */
  Optional<CategoryDto> updateCategory(Long id, CategoryDto categoryDto);

  /**
   * Delete a categoryId
   *
   * @param id categoryId id
   * @return true if categoryId is deleted, false otherwise
   */
  boolean deleteCategory(Long id);

  /**
   * Get categoryId by id
   *
   * @param id categoryId id
   * @return categoryId details
   */
  Optional<CategoryDto> getCategoryById(Long id);

  /**
   * Get all categories
   *
   * @return list of categoryId details
   */
  List<CategoryDto> getAllCategories();
}
