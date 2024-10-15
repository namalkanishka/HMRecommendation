package org.example.domain.product.repository;

import org.example.domain.product.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ProductTag entity
 *
 * @author namal
 */
@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
}
