package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.inventory.dto.InventoryDto;
import org.example.domain.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for Inventory
 *
 * @author namal
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/inventories", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryController {

  private final InventoryService inventoryService;

  /**
   * Create a new inventory
   *
   * @param inventoryDto inventory details
   * @return created inventory
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) {
    InventoryDto createdInventory = inventoryService.createInventory(inventoryDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
  }

  /**
   * Update an inventory
   *
   * @param id           inventory id
   * @param inventoryDto inventory details
   * @return updated inventory
   */
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) {
    inventoryDto = new InventoryDto(id, inventoryDto.productId(), inventoryDto.quantityOnHand(), inventoryDto.quantityOnOrder(), inventoryDto.quantityAllocated());
    Optional<InventoryDto> updatedInventory = inventoryService.updateInventory(inventoryDto);
    return updatedInventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Delete an inventory
   *
   * @param id inventory id
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
    boolean deleted = inventoryService.deleteInventory(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }

  /**
   * Get an inventory by id
   *
   * @param id inventory id
   * @return inventory details
   */
  @GetMapping("/{id}")
  public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id) {
    Optional<InventoryDto> inventory = inventoryService.getInventoryById(id);
    return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Get all inventories
   *
   * @return list of inventories
   */
  @GetMapping
  public ResponseEntity<List<InventoryDto>> getAllInventories() {
    List<InventoryDto> inventories = inventoryService.getAllInventories();
    return ResponseEntity.ok(inventories);
  }
}