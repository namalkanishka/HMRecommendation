package org.example.domain.product.service;

import org.example.domain.product.dto.EventDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Event.
 *
 * @author namal
 */
public interface EventService {
  /**
   * Create a new eventId
   *
   * @param eventDto eventId details
   * @return created eventId details
   */
  EventDto createEvent(EventDto eventDto);

  /**
   * Update an eventId
   *
   * @param eventDto eventId details
   * @return updated eventId details
   */
  Optional<EventDto> updateEvent(EventDto eventDto);

  /**
   * Delete an eventId
   *
   * @param id eventId id
   * @return true if eventId is deleted, false otherwise
   */
  boolean deleteEvent(Long id);

  /**
   * Get eventId by id
   *
   * @param id eventId id
   * @return eventId details
   */
  Optional<EventDto> getEventById(Long id);

  /**
   * Get all events
   *
   * @return list of eventId details
   */
  List<EventDto> getAllEvents();
}