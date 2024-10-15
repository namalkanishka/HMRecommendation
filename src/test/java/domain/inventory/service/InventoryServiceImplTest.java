package domain.inventory.service;

import org.example.domain.inventory.dto.InventoryDto;
import org.example.domain.inventory.entity.Inventory;
import org.example.domain.inventory.mapper.InventoryMapper;
import org.example.domain.inventory.repository.InventoryRepository;
import org.example.domain.inventory.service.InventoryService;
import org.example.domain.inventory.service.impl.InventoryServiceImpl;
import org.example.domain.product.entity.Product;
import org.example.domain.product.repository.ProductRepository;
import org.example.exception.HMCustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

  @Mock
  private InventoryRepository mockInventoryRepository;

  @Mock
  private InventoryMapper mockInventoryMapper;

  @Mock
  private ProductRepository mockProductRepository;

  private InventoryService inventoryService;

  private static final Long INVENTORY_ID = 1L;
  private static final int QUANTITY_ALLOCATED = 10;
  private static final int QUANTITY_ON_HAND = 20;
  private static final Long PRODUCT_ID = 2L;
  private static final int QUANTITY_ON_ORDER = 0;


  private InventoryDto inventoryDto;
  private Inventory inventory;

  @BeforeEach
  void setUp() {
    inventoryService = new InventoryServiceImpl(mockInventoryRepository, mockInventoryMapper, mockProductRepository);
    inventoryDto = new InventoryDto(
            INVENTORY_ID,
            PRODUCT_ID,
            QUANTITY_ON_HAND,
            QUANTITY_ON_ORDER,
            QUANTITY_ALLOCATED);

    inventory = new Inventory(
            INVENTORY_ID,
            Product.builder().productId(PRODUCT_ID).build(),
            QUANTITY_ON_HAND,
            QUANTITY_ON_ORDER,
            QUANTITY_ALLOCATED);
  }

  @Test
  void testCreateInventorySuccess() {
    when(mockInventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
    when(mockInventoryMapper.mapToDto(inventory)).thenReturn(inventoryDto);
    when(mockInventoryMapper.mapToEntity(inventoryDto)).thenReturn(inventory);
    when(mockProductRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(Product.builder().productId(PRODUCT_ID).build()));

    inventoryService.createInventory(inventoryDto);

    verify(mockInventoryRepository, times(1)).save(any(Inventory.class));
    verify(mockInventoryMapper, times(1)).mapToDto(inventory);
    verify(mockProductRepository, times(1)).findById(PRODUCT_ID);
  }

  @Test
  void testUpdateInventorySuccess() {
    when(mockInventoryRepository.findById(INVENTORY_ID)).thenReturn(Optional.of(inventory));
    when(mockInventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
    when(mockInventoryMapper.mapToDto(inventory)).thenReturn(inventoryDto);

    inventoryService.updateInventory(inventoryDto);

    verify(mockInventoryRepository, times(1)).findById(INVENTORY_ID);
    verify(mockInventoryRepository, times(1)).save(any(Inventory.class));
    verify(mockInventoryMapper, times(1)).mapToDto(inventory);
  }

  @Test
  void testUpdateInventoryNotFound() {
    when(mockInventoryRepository.findById(INVENTORY_ID)).thenReturn(Optional.empty());

    assertThrows(HMCustomException.class, () -> inventoryService.updateInventory(inventoryDto));

    verify(mockInventoryRepository, times(1)).findById(INVENTORY_ID);
    verify(mockInventoryRepository, times(0)).save(any(Inventory.class));
  }
}