package ej.domain.seatReservation.event.seatReservation;

import ej.domain.seatReservation.command.SeatUnreservedCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatUnreservedEvent extends SeatReservationEvent {

    private String bookingItemId;

    public SeatUnreservedEvent(SeatUnreservedCommand cmd) {
        this.bookingItemId = cmd.getBookingItemId();
    }

}
