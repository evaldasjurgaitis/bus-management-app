package ej.domain.seatReservation.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatReserveCommand implements SeatReservationCommand {

    private String bookingItemId;

}
