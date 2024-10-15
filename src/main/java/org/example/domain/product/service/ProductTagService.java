package org.example.domain.product.service;

import org.example.domain.product.dto.ProductTagDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for ProductTag.
 *
 * @author namal
 */
public interface ProductTagService {

  /**
   * Create a new product tag
   *
   * @param productTagDto product tag details
   * @return created product tag details
   */
  ProductTagDto createProductTag(ProductTagDto productTagDto);

  /**
   * Get product tag by id
   *
   * @param id product tag id
   * @return product tag details
   */
  Optional<ProductTagDto> getProductTagById(Long id);

  /**
   * Get all product tagsIds
   *
   * @return list of product tag details
   */
  List<ProductTagDto> getAllProductTags();

  /**
   * Update a product tag
   *
   * @param productTagDto product tag details
   * @return updated product tag details
   */
  Optional<ProductTagDto> updateProductTag(ProductTagDto productTagDto);

  /**
   * Delete a product tag
   *
   * @param id product tag id
   * @return true if product tag is deleted, false otherwise
   */
  boolean deleteProductTag(Long id);
}
