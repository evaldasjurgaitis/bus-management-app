package ej.domain.event.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingItemAddedEvent extends BookingEvent {

    private String bookingItemId;
    private Passenger passenger;
    private Trip trip;

}
