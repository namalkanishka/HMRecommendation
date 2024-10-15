package org.example.domain.inventory.dto;

import lombok.Builder;

/**
 * DTO for Inventory
 *
 * @author namal
 */
@Builder
public record InventoryDto(
        Long id,
        Long productId,
        int quantityOnHand,
        int quantityOnOrder,
        int quantityAllocated
) {
}
