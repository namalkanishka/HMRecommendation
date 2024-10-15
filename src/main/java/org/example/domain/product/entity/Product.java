package org.example.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.inventory.entity.Inventory;

import java.util.List;

/**
 * Entity class representing products.
 *
 * @author namal
 */
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  private String name;

  private String description;

  private Double minPrice;

  private Double defaultPrice;

  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "eventId", referencedColumnName = "eventId")
  private Event event;

  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
  private Category category;

  @ManyToMany(fetch = FetchType.LAZY)
  private List<ProductTag> productTags;

  @ManyToMany(fetch = FetchType.LAZY)
  private List<ProductImage> productImages;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "productId")
  private Inventory inventory;

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
    if (inventory != null) {
      inventory.setProduct(this);
    }
  }
}
