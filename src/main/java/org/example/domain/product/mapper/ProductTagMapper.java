package org.example.domain.product.mapper;

import org.example.common.Mapper;
import org.example.domain.product.dto.ProductTagDto;
import org.example.domain.product.entity.ProductTag;
import org.springframework.stereotype.Component;

/**
 * Mapper for ProductTag entity and ProductTagDto
 *
 * @author namal
 */
@Component
public class ProductTagMapper implements Mapper<ProductTag, ProductTagDto> {
  @Override
  public ProductTagDto mapToDto(ProductTag productTag) {
    return new ProductTagDto(productTag.getTagId(), productTag.getTag());
  }

  @Override
  public ProductTag mapToEntity(ProductTagDto productTagDto) {
    return new ProductTag(productTagDto.tagId(), productTagDto.tag());
  }
}
