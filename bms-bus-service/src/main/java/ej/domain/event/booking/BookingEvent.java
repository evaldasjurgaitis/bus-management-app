package ej.domain.event.booking;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "ej.domain.aggregate.Booking")
public class BookingEvent implements Event {
}
