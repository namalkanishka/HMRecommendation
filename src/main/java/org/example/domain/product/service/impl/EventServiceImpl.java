package org.example.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.product.dto.EventDto;
import org.example.domain.product.entity.Event;
import org.example.domain.product.mapper.EventMapper;
import org.example.domain.product.repository.EventRepository;
import org.example.domain.product.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of EventService
 *
 * @author namal
 */
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

  @Override
  public EventDto createEvent(EventDto eventDto) {
    Event event = eventMapper.mapToEntity(eventDto);
    return eventMapper.mapToDto(eventRepository.save(event));
  }

  @Override
  public Optional<EventDto> updateEvent(EventDto eventDto) {
    if (eventRepository.existsById(eventDto.eventId())) {
      Event event = eventMapper.mapToEntity(eventDto);
      eventRepository.save(event);
      return Optional.of(eventMapper.mapToDto(event));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean deleteEvent(Long id) {
    if (eventRepository.existsById(id)) {
      eventRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Optional<EventDto> getEventById(Long id) {
    return eventRepository.findById(id).map(eventMapper::mapToDto);
  }

  @Override
  public List<EventDto> getAllEvents() {
    return eventRepository.findAll().stream().map(eventMapper::mapToDto).toList();
  }
}