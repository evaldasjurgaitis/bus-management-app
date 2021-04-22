package ej.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelBookingCommand implements BookingCommand {

    private String bookingAggregateId;

}
