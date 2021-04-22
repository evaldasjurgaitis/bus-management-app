package ej.domain.projection.service;

import ej.domain.exception.ResourceNotFoundException;
import ej.domain.projection.entity.BookingProjection;
import ej.domain.projection.repository.BookingProjectionRepository;

public class BookingProjectionQueryService {

    private BookingProjectionRepository bookingProjectionRepository;

    public BookingProjectionQueryService(BookingProjectionRepository bookingProjectionRepository) {
        this.bookingProjectionRepository = bookingProjectionRepository;
    }

    public BookingProjection findById(String entityId) {
        return bookingProjectionRepository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

}
