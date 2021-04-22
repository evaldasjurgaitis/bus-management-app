package ej.api.response;

import ej.domain.aggregate.Booking;
import ej.domain.projection.entity.BookingProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private String bookingAggregateId;
    private String customerAggregateId;
    private List<BookingItem> bookingItems;
    private String bookingState;

    public static BookingResponse fromDomain(Booking booking, String bookingAggregateId) {
        return BookingResponse.builder()
                .bookingAggregateId(bookingAggregateId)
                .customerAggregateId(booking.getCustomerAggregateId())
                .bookingState(booking.getState().toString())
                .bookingItems(BookingItem.fromDomain(mapToBookingItems(booking.getBookingItems())))
                .build();
    }

    public static BookingResponse fromProjection(BookingProjection bookingProjection) {
        return BookingResponse.builder()
                .bookingAggregateId(bookingProjection.getAggregateId())
                .customerAggregateId(bookingProjection.getCustomerAggregateId())
                .bookingItems(BookingItem.fromProjection(bookingProjection.getBookingItems()))
                .bookingState(bookingProjection.getBookingState().toString()).build();
    }

    private static List<BookingItem> mapToBookingItems(List<ej.domain.aggregate.BookingItem> bookingItems) {
        return bookingItems.stream()
                .map(bookingItem -> mapToBookingItem(bookingItem))
                .collect(Collectors.toList());
    }

    private static BookingItem mapToBookingItem(ej.domain.aggregate.BookingItem bookingItem) {
        return new BookingItem(bookingItem.getBookingItemId(), mapToPassenger(bookingItem.getPassenger()), mapToTrip(bookingItem.getTrip()));
    }

    private static Passenger mapToPassenger(ej.domain.aggregate.Passenger passenger) {
        return new Passenger(passenger.getName(), passenger.getSurname(), passenger.getPhone());
    }

    private static Trip mapToTrip(ej.domain.aggregate.Trip trip) {
        return Trip.builder()
                .id(trip.getId())
                .arrivalFrom(trip.getArrivalFrom())
                .arrivalTime(trip.getArrivalTime().toLocalDateTime())
                .departureTime(trip.getDepartureTime().toLocalDateTime())
                .departureTo(trip.getDepartureTo())
                .distance(trip.getDistance())
                .travelTime(trip.getTravelTime())
                .build();
    }

}
