package org.example.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO for Product
 *
 * @author namal
 */
@Builder
public record ProductDto(
        Long productId,
        String name,
        Double defaultPrice,
        List<ProductImageDto> productImages
) {
}
