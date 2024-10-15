package org.example.domain.product.service;

import org.example.domain.product.dto.ProductDetailDto;
import org.example.domain.product.dto.ProductDto;
import org.example.domain.recommendation.dto.PreferenceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service for product related operations
 *
 * @author namal
 */
public interface ProductService {

  /**
   * Create a new product
   *
   * @param productDetailDto product details
   * @return created product details
   */
  ProductDetailDto createProduct(ProductDetailDto productDetailDto);

  /**
   * Update a product
   *
   * @param productDetailDto product details
   * @return updated product details
   */
  Optional<ProductDetailDto> updateProduct(ProductDetailDto productDetailDto);

  /**
   * Delete a product
   *
   * @param id product id
   * @return true if product is deleted, false otherwise
   */
  boolean deleteProduct(Long id);

  /**
   * Get product by id
   *
   * @param id product id
   * @return product details
   */
  Optional<ProductDetailDto> getProductById(Long id);

  /**
   * Get all products
   *
   * @return list of product details
   */
  List<ProductDetailDto> getAllProducts();

  /**
   * Get products by preference
   *
   * @param preferenceDto user preference details
   * @param pageable      pageable product details
   * @return list of product details
   */
  Page<ProductDto> getProductsByPreference(PreferenceDto preferenceDto, Pageable pageable);
}
