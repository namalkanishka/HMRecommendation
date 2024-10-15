package org.example.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity for ProductTag
 *
 * @author namal
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tags")
public class ProductTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tagId;

  private String tag;
}
