package domain.product.mapper;

import org.example.domain.product.dto.EventDto;
import org.example.domain.product.entity.Event;
import org.example.domain.product.mapper.EventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EventMapperTest {

  private EventMapper eventMapper;

  private static final Long EVENT_ID = 1L;
  private static final String EVENT_NAME = "Test Event";

  @BeforeEach
  void setUp() {
    eventMapper = new EventMapper();
  }

  @Test
  void testMapToDto() {
    Event event = new Event(EVENT_ID, EVENT_NAME);

    EventDto eventDto = eventMapper.mapToDto(event);

    assertEquals(EVENT_ID, eventDto.eventId());
    assertEquals(EVENT_NAME, eventDto.name());
  }

  @Test
  void testMapToEntity() {
    EventDto eventDto = new EventDto(EVENT_ID, EVENT_NAME);

    Event event = eventMapper.mapToEntity(eventDto);

    assertEquals(EVENT_ID, event.getEventId());
    assertEquals(EVENT_NAME, event.getName());
  }
}
