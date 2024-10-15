package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.product.dto.EventDto;
import org.example.domain.product.service.EventService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for Event
 * @author namal
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

  private final EventService eventService;

  /**
   * Create a new eventId
   *
   * @param eventDto eventId details
   * @return created eventId
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
    EventDto createdEvent = eventService.createEvent(eventDto);
    return ResponseEntity.ok(createdEvent);
  }

  /**
   * Update an eventId
   *
   * @param id       eventId id
   * @param eventDto eventId details
   * @return updated eventId
   */
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
    eventDto = new EventDto(id, eventDto.name());
    Optional<EventDto> updatedEvent = eventService.updateEvent(eventDto);
    return updatedEvent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Delete an eventId
   *
   * @param id eventId id
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
    boolean deleted = eventService.deleteEvent(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }

  /**
   * Get an eventId by id
   *
   * @param id eventId id
   * @return eventId details
   */
  @GetMapping("/{id}")
  public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
    Optional<EventDto> event = eventService.getEventById(id);
    return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Get all events
   *
   * @return list of events
   */
  @GetMapping
  public ResponseEntity<List<EventDto>> getAllEvents() {
    List<EventDto> events = eventService.getAllEvents();
    return ResponseEntity.ok(events);
  }
}