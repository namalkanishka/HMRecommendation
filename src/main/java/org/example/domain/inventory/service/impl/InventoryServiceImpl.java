package org.example.domain.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.inventory.dto.InventoryDto;
import org.example.domain.inventory.entity.Inventory;
import org.example.domain.inventory.mapper.InventoryMapper;
import org.example.domain.inventory.repository.InventoryRepository;
import org.example.domain.inventory.service.InventoryService;
import org.example.domain.product.entity.Product;
import org.example.domain.product.repository.ProductRepository;
import org.example.exception.HMCustomException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for Inventory
 *
 * @author namal
 */
@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  private final InventoryMapper inventoryMapper;

  private final ProductRepository productRepository;

  @Override
  public InventoryDto createInventory(InventoryDto inventoryDto) {
    Optional<Product> product = productRepository.findById(inventoryDto.productId());

    if (product.isEmpty()) {
      throw new HMCustomException("Product not found");
    }

    Inventory inventory = inventoryMapper.mapToEntity(inventoryDto);
    inventory.setProduct(product.get());

    return inventoryMapper.mapToDto(inventoryRepository.save(inventory));
  }

  @Override
  public Optional<InventoryDto> updateInventory(InventoryDto inventoryDto) {

    Optional<Inventory> inventory = inventoryRepository.findById(inventoryDto.id());

    if (inventory.isEmpty()) {
      throw new HMCustomException("Inventory not found");
    }

    Inventory inventoryToUpdate = inventory.get();
    inventoryToUpdate.setProduct(inventory.get().getProduct());
    inventoryToUpdate.setQuantityOnHand(inventoryDto.quantityOnHand());
    inventoryToUpdate.setQuantityOnOrder(inventoryDto.quantityOnOrder());
    inventoryToUpdate.setQuantityAllocated(inventoryDto.quantityAllocated());

    return Optional.of(inventoryMapper.mapToDto(inventoryRepository.save(inventoryToUpdate)));
  }

  @Override
  public boolean deleteInventory(Long id) {
    if (inventoryRepository.existsById(id)) {
      inventoryRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Optional<InventoryDto> getInventoryById(Long id) {
    return inventoryRepository.findById(id).map(inventoryMapper::mapToDto);
  }

  @Override
  public List<InventoryDto> getAllInventories() {
    return inventoryRepository.findAll().stream().map(inventoryMapper::mapToDto).toList();
  }
}