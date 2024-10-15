package org.example.common;

/**
 * Mapper interface for mapping entities to DTOs and vice versa
 *
 * @param <T> Entity
 * @param <U> DTO
 * @author namal
 */
public interface Mapper<T, U> {

  /**
   * Maps entity to DTO
   *
   * @param t Entity
   * @return DTO
   */
  U mapToDto(T t);

  /**
   * Maps DTO to entity
   *
   * @param u DTO
   * @return Entity
   */
  T mapToEntity(U u);
}
