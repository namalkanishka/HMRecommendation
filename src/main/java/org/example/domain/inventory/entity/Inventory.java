package org.example.domain.inventory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.product.entity.Product;

/**
 * Entity for Inventory
 *
 * @author namal
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  private int quantityOnHand;

  private int quantityOnOrder;

  private int quantityAllocated;
}
