package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.domain.product.dto.CategoryDto;
import org.example.domain.product.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for Category
 * @author namal
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

  private final CategoryService categoryService;

  /**
   * Create a new categoryId
   *
   * @param categoryDto categoryId details
   * @return created categoryId
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
    CategoryDto createdCategory = categoryService.createCategory(categoryDto);
    return ResponseEntity.ok(createdCategory);
  }

  /**
   * Update a categoryId
   *
   * @param id          categoryId id
   * @param categoryDto categoryId details
   * @return updated categoryId
   */
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
    Optional<CategoryDto> updatedCategory = categoryService.updateCategory(id, categoryDto);
    return updatedCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Delete a categoryId
   *
   * @param id categoryId id
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    boolean deleted = categoryService.deleteCategory(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Get categoryId by id
   *
   * @param id categoryId id
   * @return CategoryDto
   */
  @GetMapping("/{id}")
  public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
    Optional<CategoryDto> categoryDto = categoryService.getCategoryById(id);
    return categoryDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Get all categories
   *
   * @return list of categories
   */
  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    List<CategoryDto> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(categories);
  }
}