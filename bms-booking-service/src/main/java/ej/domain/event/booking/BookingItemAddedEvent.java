package ej.domain.event.booking;

import ej.domain.aggregate.Passenger;
import ej.domain.aggregate.Trip;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingItemAddedEvent extends BookingEvent {

    private String bookingItemId;
    private Passenger passenger;
    private Trip trip;

}
