package org.example.domain.product.repository;

import org.example.domain.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Category entity
 *
 * @author namal
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
