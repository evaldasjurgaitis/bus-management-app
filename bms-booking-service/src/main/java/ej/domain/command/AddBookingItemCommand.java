package ej.domain.command;

import ej.api.request.AddBookingItemRequest;
import ej.domain.aggregate.Passenger;
import ej.domain.aggregate.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookingItemCommand implements BookingCommand {

    private String bookingItemId;
    private Passenger passenger;
    private Trip trip;
    private String customerAggregateId;

    public AddBookingItemCommand(AddBookingItemRequest request) {
        this.passenger = new Passenger(request.getPassenger().getName(), request.getPassenger().getSurname(), request.getPassenger().getPhone());
        this.trip = new Trip(
                request.getTrip().getId(),
                Timestamp.valueOf(request.getTrip().getArrivalTime()),
                Timestamp.valueOf(request.getTrip().getDepartureTime()),
                request.getTrip().getDistance(),
                request.getTrip().getTravelTime(),
                request.getTrip().getArrivalFrom(),
                request.getTrip().getDepartureTo()
        );
        this.customerAggregateId = request.getCustomerAggregateId();
        this.bookingItemId = request.getBookingItemId();
    }

}
