package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.inventory.entity.Inventory;
import org.example.domain.product.dto.*;
import org.example.domain.product.entity.*;
import org.example.domain.product.mapper.ProductImageMapper;
import org.example.domain.product.mapper.ProductMapper;
import org.example.domain.product.mapper.ProductTagMapper;
import org.example.domain.product.repository.*;
import org.example.domain.product.service.CategoryService;
import org.example.domain.product.service.EventService;
import org.example.domain.product.service.ProductService;
import org.example.domain.product.service.ProductTagService;
import org.example.domain.recommendation.dto.PreferenceDto;
import org.example.exception.HMCustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of ProductService
 *
 * @author namal
 */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ProductMapper productMapper;

  private final CategoryService categoryService;

  private final ProductTagService productTagService;

  private final EventService eventService;

  private final ProductImageRepository productImageRepository;

  private final ProductImageMapper productImageMapper;

  private final ProductTagMapper productTagMapper;

  /**
   * Create a new product
   *
   * @param productDetailDto product details
   * @return created product details
   * @throws HMCustomException if categoryId or eventId is not found
   */
  @Override
  public ProductDetailDto createProduct(ProductDetailDto productDetailDto) {
    Product product = productMapper.mapToEntity(productDetailDto);

    validateCategory(productDetailDto.category().categoryId());
    validateEvent(productDetailDto.event().eventId());

    saveProductImages(product.getProductImages());
    setProductTags(productDetailDto, product);

    Inventory inventory = Inventory.builder()
            .quantityOnHand(0)
            .quantityAllocated(0)
            .quantityOnOrder(0)
            .build();

    product.setInventory(inventory);

    return productMapper.mapToDto(productRepository.save(product));

  }

  /**
   * Update a product
   *
   * @param productDetailDto product details
   * @return updated product details
   * @throws HMCustomException if product is not found
   */
  @Override
  public Optional<ProductDetailDto> updateProduct(ProductDetailDto productDetailDto) {
    if (productRepository.existsById(productDetailDto.productId())) {
      Product product = productMapper.mapToEntity(productDetailDto);
      productRepository.save(product);
      return Optional.of(productMapper.mapToDto(product));
    } else {
      return Optional.empty();
    }
  }

  /**
   * Delete a product
   *
   * @param id product id
   * @return true if product is deleted, false otherwise
   */
  @Override
  public boolean deleteProduct(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get product by id
   *
   * @param id product id
   * @return product details
   */
  @Override
  public Optional<ProductDetailDto> getProductById(Long id) {
    return productRepository.findById(id).map(productMapper::mapToDto);
  }

  /**
   * Get all products
   *
   * @return list of product details
   */
  @Override
  public List<ProductDetailDto> getAllProducts() {
    return productRepository.findAll().stream().map(productMapper::mapToDto).toList();
  }

  /**
   * Get products by preference
   *
   * @param preferenceDto user preference details
   * @param pageable      pagination details
   * @return list of product details
   */
  @Override
  public Page<ProductDto> getProductsByPreference(PreferenceDto preferenceDto, Pageable pageable) {
    Page<Product> products;
    if (preferenceDto.tagsIds().isEmpty()) {
      products = productRepository
              .findProductsByCategoryEventAndBudget(
                      preferenceDto.categoryId(),
                      preferenceDto.eventId(),
                      preferenceDto.minBudget(),
                      preferenceDto.maxBudget(),
                      pageable);
    } else {
      products = productRepository
              .findProductsByCategoryEventBudgetAndTags(
                      preferenceDto.categoryId(),
                      preferenceDto.eventId(),
                      preferenceDto.minBudget(),
                      preferenceDto.maxBudget(),
                      preferenceDto.tagsIds(),
                      pageable);
    }

    return products.map(product -> new ProductDto(
            product.getProductId(),
            product.getName(),
            product.getDefaultPrice(),
            product.getProductImages().stream().map(productImageMapper::mapToDto).toList()));
  }

  private void validateCategory(Long categoryId) {
    Optional<CategoryDto> category = categoryService.getCategoryById(categoryId);
    if (category.isEmpty()) {
      throw new HMCustomException("Invalid categoryId");
    }
  }

  private void validateEvent(Long eventId) {
    Optional<EventDto> event = eventService.getEventById(eventId);
    if (event.isEmpty()) {
      throw new HMCustomException("Invalid eventId id");
    }
  }

  private void saveProductImages(List<ProductImage> productImages) {
    productImageRepository.saveAll(productImages);
  }

  private void setProductTags(ProductDetailDto productDetailDto, Product product) {
    List<ProductTagDto> productTags = new ArrayList<>();
    for (var productTag : productDetailDto.productTags()) {
      productTagService.getProductTagById(productTag.tagId()).ifPresent(productTags::add);
    }
    product.setProductTags(productTags.stream().map(productTagMapper::mapToEntity).toList());
  }
}
