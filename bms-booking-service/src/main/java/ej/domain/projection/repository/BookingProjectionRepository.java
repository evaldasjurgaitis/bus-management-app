package ej.domain.projection.repository;

import ej.domain.projection.entity.BookingProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingProjectionRepository extends CrudRepository<BookingProjection, String> {
}
