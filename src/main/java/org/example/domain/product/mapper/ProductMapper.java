package org.example.domain.product.mapper;

import lombok.RequiredArgsConstructor;
import org.example.common.Mapper;
import org.example.domain.inventory.entity.Inventory;
import org.example.domain.inventory.mapper.InventoryMapper;
import org.example.domain.product.dto.ProductDetailDto;
import org.example.domain.product.entity.Product;
import org.springframework.stereotype.Component;

/**
 * Mapper for Product entity and ProductDetailDto
 *
 * @author namal
 */
@RequiredArgsConstructor
@Component
public class ProductMapper implements Mapper<Product, ProductDetailDto> {

  private final CategoryMapper categoryMapper;

  private final ProductTagMapper productTagMapper;

  private final ProductImageMapper productImageMapper;

  private final EventMapper eventMapper;

  private final InventoryMapper inventoryMapper;

  @Override
  public ProductDetailDto mapToDto(Product product) {
    return new ProductDetailDto(product.getProductId(),
            product.getName(),
            product.getDescription(),
            product.getMinPrice(),
            product.getDefaultPrice(),
            eventMapper.mapToDto(product.getEvent()),
            categoryMapper.mapToDto(product.getCategory()),
            product.getProductTags().stream().map(productTagMapper::mapToDto).toList(),
            product.getProductImages().stream().map(productImageMapper::mapToDto).toList(),
            inventoryMapper.mapToDto(product.getInventory())
    );
  }

  @Override
  public Product mapToEntity(ProductDetailDto productDetailDto) {
    return new Product(productDetailDto.productId(),
            productDetailDto.name(),
            productDetailDto.description(),
            productDetailDto.minPrice(),
            productDetailDto.defaultPrice(),
            eventMapper.mapToEntity(productDetailDto.event()),
            categoryMapper.mapToEntity(productDetailDto.category()),
            productDetailDto.productTags().stream().map(productTagMapper::mapToEntity).toList(),
            productDetailDto.productImages().stream().map(productImageMapper::mapToEntity).toList(),
            productDetailDto.inventory() == null ? new Inventory() :inventoryMapper.mapToEntity(productDetailDto.inventory())
    );
  }
}
