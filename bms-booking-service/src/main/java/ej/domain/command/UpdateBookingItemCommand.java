package ej.domain.command;

import ej.domain.aggregate.Passenger;
import ej.domain.aggregate.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingItemCommand implements BookingCommand {

    private String bookingItemId;
    private Passenger passenger;
    private Trip trip;

}
