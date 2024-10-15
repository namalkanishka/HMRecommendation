package org.example.domain.product.mapper;

import org.example.common.Mapper;
import org.example.domain.product.dto.EventDto;
import org.example.domain.product.entity.Event;
import org.springframework.stereotype.Component;

/**
 * Mapper for Event entity and EventDto
 *
 * @author namal
 */
@Component
public class EventMapper implements Mapper<Event, EventDto> {
  @Override
  public EventDto mapToDto(Event event) {
    return new EventDto(event.getEventId(), event.getName());
  }

  @Override
  public Event mapToEntity(EventDto eventDto) {
    return new Event(eventDto.eventId(), eventDto.name());
  }
}
