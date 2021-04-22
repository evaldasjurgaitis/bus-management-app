package ej.domain.service;

import ej.domain.aggregate.Booking;
import ej.domain.command.BookingCommand;
import ej.domain.projection.entity.BookingProjection;
import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

public class BookingService {

    private final AggregateRepository<Booking, BookingCommand> bookingAggregateRepository;

    public BookingService(AggregateRepository<Booking, BookingCommand> bookingAggregateRepository) {
        this.bookingAggregateRepository = bookingAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<Booking>> createBooking(BookingProjection bookingProjection, BookingCommand command) {
        if (bookingProjection == null) {
            return bookingAggregateRepository.save(command);
        }

        return updateBooking(bookingProjection.getAggregateId(), command);
    }

    public CompletableFuture<EntityWithIdAndVersion<Booking>> updateBooking(String bookingId, BookingCommand command) {
        return bookingAggregateRepository.update(bookingId, command);
    }

}
