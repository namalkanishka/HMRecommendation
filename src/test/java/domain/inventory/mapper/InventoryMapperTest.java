package domain.inventory.mapper;

import org.example.domain.inventory.dto.InventoryDto;
import org.example.domain.inventory.entity.Inventory;
import org.example.domain.inventory.mapper.InventoryMapper;
import org.example.domain.product.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class InventoryMapperTest {

  private InventoryMapper inventoryMapper;

  private static final Long INVENTORY_ID = 1L;
  private static final Long PRODUCT_ID = 2L;
  private static final int QUANTITY_ON_HAND = 20;
  private static final int QUANTITY_ON_ORDER = 10;
  private static final int QUANTITY_ALLOCATED = 5;

  @BeforeEach
  void setUp() {
    inventoryMapper = new InventoryMapper();
  }

  @Test
  void testMapToDto() {
    Inventory inventory = new Inventory(
            INVENTORY_ID,
            Product.builder().productId(PRODUCT_ID).build(),
            QUANTITY_ON_HAND,
            QUANTITY_ON_ORDER,
            QUANTITY_ALLOCATED
    );

    InventoryDto inventoryDto = inventoryMapper.mapToDto(inventory);

    assertEquals(INVENTORY_ID, inventoryDto.id());
    assertEquals(PRODUCT_ID, inventoryDto.productId());
    assertEquals(QUANTITY_ON_HAND, inventoryDto.quantityOnHand());
    assertEquals(QUANTITY_ON_ORDER, inventoryDto.quantityOnOrder());
    assertEquals(QUANTITY_ALLOCATED, inventoryDto.quantityAllocated());
  }

  @Test
  void testMapToEntity() {
    InventoryDto inventoryDto = new InventoryDto(
            INVENTORY_ID,
            PRODUCT_ID,
            QUANTITY_ON_HAND,
            QUANTITY_ON_ORDER,
            QUANTITY_ALLOCATED
    );

    Inventory inventory = inventoryMapper.mapToEntity(inventoryDto);

    assertEquals(INVENTORY_ID, inventory.getId());
    assertEquals(QUANTITY_ON_HAND, inventory.getQuantityOnHand());
    assertEquals(QUANTITY_ON_ORDER, inventory.getQuantityOnOrder());
    assertEquals(QUANTITY_ALLOCATED, inventory.getQuantityAllocated());
  }
}