package org.example.domain.inventory.service;

import org.example.domain.inventory.dto.InventoryDto;

import java.util.List;
import java.util.Optional;

/**
 * Service for Inventory
 *
 * @author namal
 */
public interface InventoryService {

  /**
   * Create inventory
   *
   * @param inventoryDto inventoryDto
   * @return created inventory
   */
  InventoryDto createInventory(InventoryDto inventoryDto);

  /**
   * Update inventory
   *
   * @param inventoryDto inventoryDto
   * @return updated inventory
   */
  Optional<InventoryDto> updateInventory(InventoryDto inventoryDto);

  /**
   * Delete inventory
   *
   * @param id inventory id
   * @return true if deleted
   */
  boolean deleteInventory(Long id);

  /**
   * Get inventory by id
   *
   * @param id inventory id
   * @return inventory
   */
  Optional<InventoryDto> getInventoryById(Long id);

  /**
   * Get all inventories
   *
   * @return list of inventories
   */
  List<InventoryDto> getAllInventories();
}
