package ej.domain.seatReservation.projection.service;

import ej.domain.seatReservation.projection.entity.SeatReservationProjection;
import ej.domain.seatReservation.projection.repository.SeatReservationProjectionRepository;

public class SeatReservationProjectionService {

    private SeatReservationProjectionRepository seatReservationProjectionRepository;

    public SeatReservationProjectionService(SeatReservationProjectionRepository seatReservationProjectionRepository) {
        this.seatReservationProjectionRepository = seatReservationProjectionRepository;
    }

    public void save(SeatReservationProjection entity) {
        seatReservationProjectionRepository.save(entity);
    }

}
