package org.example.domain.product.repository;

import org.example.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for ProductImage entity
 *
 * @author namal
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
