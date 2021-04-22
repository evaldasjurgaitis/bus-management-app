package ej.domain.seatReservation.event.seatReservation;

import ej.domain.seatReservation.command.SeatReserveCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatReservedEvent extends SeatReservationEvent {

    private String bookingItemId;

    public SeatReservedEvent(SeatReserveCommand cmd) {
        this.bookingItemId = cmd.getBookingItemId();
    }

}
