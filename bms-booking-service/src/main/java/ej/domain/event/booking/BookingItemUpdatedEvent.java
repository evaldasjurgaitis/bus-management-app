package ej.domain.event.booking;

import ej.domain.aggregate.Passenger;
import ej.domain.aggregate.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookingItemUpdatedEvent extends BookingEvent {

    private String bookingItemId;
    private Passenger passenger;
    private Trip trip;

    public BookingItemUpdatedEvent(String bookingItemId, Passenger passenger, Trip trip) {
        this.bookingItemId = bookingItemId;
        this.passenger = passenger;
        this.trip = trip;
    }

}
