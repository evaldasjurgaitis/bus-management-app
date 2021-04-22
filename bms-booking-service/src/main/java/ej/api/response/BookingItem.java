package ej.api.response;

import ej.domain.projection.entity.BookingItemProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingItem {

    private String bookingItemId;
    private Passenger passenger;
    private Trip trip;

    public static List<BookingItem> fromDomain(List<BookingItem> bookingItems) {
        return bookingItems.stream().map(bookingItem -> fromDomain(bookingItem)).collect(toList());
    }

    public static BookingItem fromDomain(BookingItem bookingItem) {
        return BookingItem.builder()
                .bookingItemId(bookingItem.getBookingItemId())
                .passenger(Passenger.fromDomain(bookingItem.getPassenger()))
                .trip(Trip.fromDomain(bookingItem.getTrip())).build();
    }

    public static List<BookingItem> fromProjection(List<BookingItemProjection> bookingItemProjections) {
        return bookingItemProjections.stream().map(BookingItem::fromProjection).collect(toList());
    }

    public static BookingItem fromProjection(BookingItemProjection bookingItemProjection) {
        return BookingItem.builder()
                .bookingItemId(bookingItemProjection.getBookingItemId())
                .passenger(Passenger.fromProjection(bookingItemProjection.getPassenger()))
                .trip(Trip.fromProjection(bookingItemProjection.getTrip())).build();
    }

}
