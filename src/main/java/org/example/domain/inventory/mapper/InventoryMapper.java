package org.example.domain.inventory.mapper;

import lombok.RequiredArgsConstructor;
import org.example.common.Mapper;
import org.example.domain.inventory.dto.InventoryDto;
import org.example.domain.inventory.entity.Inventory;
import org.springframework.stereotype.Component;

/**
 * Mapper for Inventory
 *
 * @author namal
 */
@RequiredArgsConstructor
@Component
public class InventoryMapper implements Mapper<Inventory, InventoryDto> {

  @Override
  public InventoryDto mapToDto(Inventory inventory) {

    return new InventoryDto(
            inventory.getId(),
            inventory.getProduct().getProductId(),
            inventory.getQuantityOnHand(),
            inventory.getQuantityOnOrder(),
            inventory.getQuantityAllocated()
    );
  }

  @Override
  public Inventory mapToEntity(InventoryDto inventoryDto) {

    return Inventory.builder()
            .id(inventoryDto.id())
            .quantityOnHand(inventoryDto.quantityOnHand())
            .quantityOnOrder(inventoryDto.quantityOnOrder())
            .quantityAllocated(inventoryDto.quantityAllocated())
            .build();
  }
}
