package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.product.dto.ProductTagDto;
import org.example.domain.product.service.ProductTagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for ProductTag
 *
 * @author namal
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/product-tags", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductTagController {

  private final ProductTagService productTagService;

  /**
   * Create a new product tag
   *
   * @param productTagDto product tag details
   * @return created product tag
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductTagDto> createProductTag(@RequestBody ProductTagDto productTagDto) {
    ProductTagDto createdProductTag = productTagService.createProductTag(productTagDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProductTag);
  }

  /**
   * Get all product tagsIds
   *
   * @return list of product tagsIds
   */
  @GetMapping
  public ResponseEntity<List<ProductTagDto>> getAllProductTags() {
    List<ProductTagDto> productTags = productTagService.getAllProductTags();
    return ResponseEntity.ok(productTags);
  }

  /**
   * Get a product tag by id
   *
   * @param id product tag id
   * @return product tag
   */
  @GetMapping("/{id}")
  public ResponseEntity<ProductTagDto> getProductTagById(@PathVariable Long id) {
    Optional<ProductTagDto> productTagDto = productTagService.getProductTagById(id);
    return productTagDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Delete a product tag
   *
   * @param id product tag id
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProductTag(@PathVariable Long id) {
    boolean deleted = productTagService.deleteProductTag(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Update a product tag
   *
   * @param productTagDto product tag details
   * @return updated product tag
   */
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductTagDto> updateProductTag(@RequestBody ProductTagDto productTagDto) {
    Optional<ProductTagDto> updatedProductTag = productTagService.updateProductTag(productTagDto);
    return updatedProductTag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
