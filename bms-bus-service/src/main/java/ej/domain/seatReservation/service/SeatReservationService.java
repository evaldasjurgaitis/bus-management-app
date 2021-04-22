package ej.domain.seatReservation.service;

import ej.domain.seatReservation.aggregate.SeatReservation;
import ej.domain.seatReservation.command.SeatReservationCommand;
import ej.domain.seatReservation.projection.entity.SeatReservationProjection;
import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

public class SeatReservationService {

    private final AggregateRepository<SeatReservation, SeatReservationCommand> seatReservationAggregateRepository;

    public SeatReservationService(AggregateRepository<SeatReservation, SeatReservationCommand> seatReservationAggregateRepository) {
        this.seatReservationAggregateRepository = seatReservationAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<SeatReservation>> createSeatReservation(SeatReservationProjection seatReservationProjection, SeatReservationCommand command) {
        if (seatReservationProjection == null) {
            return seatReservationAggregateRepository.save(command);
        }

        return updateSeatReservation(seatReservationProjection.getAggregateId(), command);
    }

    public CompletableFuture<EntityWithIdAndVersion<SeatReservation>> updateSeatReservation(String bookingId, SeatReservationCommand command) {
        return seatReservationAggregateRepository.update(bookingId, command);
    }

}
