package ej.domain.seatReservation.projection.service;

import ej.domain.seatReservation.projection.repository.SeatReservationProjectionRepository;

public class SeatReservationProjectionQueryService {

    private SeatReservationProjectionRepository seatReservationProjectionRepository;

    public SeatReservationProjectionQueryService(SeatReservationProjectionRepository seatReservationProjectionRepository) {
        this.seatReservationProjectionRepository = seatReservationProjectionRepository;
    }


}
