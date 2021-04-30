package ej.domain.event.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingCreatedEvent extends BookingEvent {

    private String customerAggregateId;
    private BookingState bookingState;

}
