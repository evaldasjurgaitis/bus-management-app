package ej.domain.seatReservation.event.seatReservation;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "ej.domain.seatReservation.aggregate.SeatReservation")
public class SeatReservationEvent implements Event {
}
