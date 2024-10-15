package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.product.dto.ProductTagDto;
import org.example.domain.product.mapper.ProductTagMapper;
import org.example.domain.product.repository.ProductTagRepository;
import org.example.domain.product.service.ProductTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ProductTagService
 *
 * @author namal
 */
@RequiredArgsConstructor
@Service
public class ProductTagImpl implements ProductTagService {

  private final ProductTagRepository productTagRepository;

  private final ProductTagMapper productTagMapper;

  @Override
  public ProductTagDto createProductTag(ProductTagDto productTagDto) {
    return productTagMapper.mapToDto(productTagRepository.save(productTagMapper.mapToEntity(productTagDto)));
  }

  @Override
  public Optional<ProductTagDto> getProductTagById(Long id) {
    return productTagRepository.findById(id).map(productTagMapper::mapToDto);
  }

  @Override
  public List<ProductTagDto> getAllProductTags() {
    return productTagRepository.findAll().stream().map(productTagMapper::mapToDto).toList();
  }

  @Override
  public Optional<ProductTagDto> updateProductTag(ProductTagDto productTagDto) {
    if (productTagRepository.existsById(productTagDto.tagId())) {
      productTagRepository.save(productTagMapper.mapToEntity(productTagDto));
      return Optional.of(productTagDto);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean deleteProductTag(Long id) {
    if (productTagRepository.existsById(id)) {
      productTagRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
