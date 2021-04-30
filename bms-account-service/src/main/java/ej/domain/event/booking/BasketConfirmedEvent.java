package ej.domain.event.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketConfirmedEvent extends BookingEvent {

    private String customerAggregateId;
    private String bookingAggregateId;

}
