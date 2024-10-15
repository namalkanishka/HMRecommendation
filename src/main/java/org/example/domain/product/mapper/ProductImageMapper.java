package org.example.domain.product.mapper;

import org.example.common.Mapper;
import org.example.domain.product.dto.ProductImageDto;
import org.example.domain.product.entity.ProductImage;
import org.springframework.stereotype.Component;

/**
 * Mapper for ProductImage entity and ProductImageDto
 *
 * @author namal
 */
@Component
public class ProductImageMapper implements Mapper<ProductImage, ProductImageDto> {

  @Override
  public ProductImageDto mapToDto(ProductImage productImage) {
    return new ProductImageDto(productImage.getImageId(), productImage.getImageUri());
  }

  @Override
  public ProductImage mapToEntity(ProductImageDto productImageDto) {
    return new ProductImage(productImageDto.imageId(), productImageDto.url());
  }
}
