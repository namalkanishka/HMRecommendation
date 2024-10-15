package org.example.domain.product.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.domain.inventory.dto.InventoryDto;

import java.util.List;

/**
 * DTO for ProductDetail
 *
 * @author namal
 */
@Builder
public record ProductDetailDto(
        Long productId,
        @NotNull @NotEmpty String name,
        @NotNull String description,
        @NotNull Double minPrice,
        @NotNull Double defaultPrice,
        @NotNull EventDto event,
        @NotNull CategoryDto category,
        @NotNull List<ProductTagDto> productTags,
        @NotNull List<ProductImageDto> productImages,
        InventoryDto inventory
) {
}
