package org.example.domain.product.repository;

import org.example.domain.product.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Event entity
 *
 * @author namal
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
