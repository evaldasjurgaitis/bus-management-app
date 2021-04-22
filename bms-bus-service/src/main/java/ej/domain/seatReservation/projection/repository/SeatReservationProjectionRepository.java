package ej.domain.seatReservation.projection.repository;

import ej.domain.seatReservation.projection.entity.SeatReservationProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface SeatReservationProjectionRepository extends CrudRepository<SeatReservationProjection, String> {
}
