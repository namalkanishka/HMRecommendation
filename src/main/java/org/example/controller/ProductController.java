package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.domain.product.dto.ProductDetailDto;
import org.example.domain.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for Product
 *
 * @author namal
 */
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

  private final ProductService productService;

  /**
   * Create a new product
   *
   * @param productDetailDto product details
   * @return created product
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductDetailDto> createProduct(@Valid @RequestBody ProductDetailDto productDetailDto) {
    ProductDetailDto createdProduct = productService.createProduct(productDetailDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  /**
   * Update a product
   *
   * @param productDetailDto product details
   * @return updated product
   */
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductDetailDto> updateProduct(@Valid @RequestBody ProductDetailDto productDetailDto) {
    Optional<ProductDetailDto> updatedProduct = productService.updateProduct(productDetailDto);
    return updatedProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Delete a product
   *
   * @param id product id
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    boolean deleted = productService.deleteProduct(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Get a product by id
   *
   * @param id product id
   * @return product details
   */
  @GetMapping("/{id}")
  public ResponseEntity<ProductDetailDto> getProductById(@PathVariable Long id) {
    Optional<ProductDetailDto> productDto = productService.getProductById(id);
    return productDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Get all products
   *
   * @return list of products
   */
  @GetMapping
  public ResponseEntity<List<ProductDetailDto>> getAllProducts() {
    List<ProductDetailDto> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }
}
