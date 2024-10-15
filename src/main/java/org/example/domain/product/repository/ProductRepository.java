package org.example.domain.product.repository;

import org.example.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Product entity
 *
 * @author namal
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("""
          SELECT p
            FROM Product p
            JOIN p.inventory pi
            JOIN p.productTags pt
            WHERE p.category.categoryId = :categoryId
            AND p.event.eventId = :eventId
            AND p.defaultPrice >= :minBudget
            AND p.defaultPrice <= :maxBudget
            AND pi.quantityOnHand > 0
            AND pt.tagId IN :tagId
            GROUP BY p
            ORDER BY COUNT(pt) DESC
          """)
  Page<Product> findProductsByCategoryEventBudgetAndTags(
          @Param("categoryId") Long categoryId,
          @Param("eventId") Long eventId,
          @Param("minBudget") Double minBudget,
          @Param("maxBudget") Double maxBudget,
          @Param("tagId") List<Long> tagId,
          Pageable pageable
  );

  @Query("""
          SELECT p
            FROM Product p
            JOIN p.inventory pi
            WHERE p.category.categoryId = :categoryId
            AND p.event.eventId = :eventId
            AND p.defaultPrice >= :minBudget
            AND p.defaultPrice <= :maxBudget
            AND pi.quantityOnHand > 0
          """)
  Page<Product> findProductsByCategoryEventAndBudget(
          @Param("categoryId") Long categoryId,
          @Param("eventId") Long eventId,
          @Param("minBudget") Double minBudget,
          @Param("maxBudget") Double maxBudget,
          Pageable pageable
  );
}
