package org.example.domain.inventory.repository;

import org.example.domain.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Inventory
 * author namal
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
