package ej.domain.projection.service;

import ej.domain.projection.entity.BookingProjection;
import ej.domain.projection.repository.BookingProjectionRepository;

public class BookingProjectionService {

    private final BookingProjectionRepository bookingProjectionRepository;

    public BookingProjectionService(BookingProjectionRepository bookingProjectionRepository) {
        this.bookingProjectionRepository = bookingProjectionRepository;
    }

    public void save(BookingProjection entity) {
        bookingProjectionRepository.save(entity);
    }

}
