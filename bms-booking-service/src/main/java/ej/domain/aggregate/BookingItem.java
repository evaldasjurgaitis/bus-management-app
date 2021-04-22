package ej.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingItem {

    private String bookingItemId;
    private Passenger passenger;
    private Trip trip;

    public BookingItem updateBookingItem(BookingItem item) {
        this.passenger = item.passenger;
        this.trip = item.trip;
        return this;
    }

}
