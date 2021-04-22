package ej.idempotent.repository;

import ej.idempotent.entity.ProcessedBusEvent;
import ej.idempotent.entity.ProcessedSeatReservationEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedSeatReservationEventRepository extends ProcessedEventRepository<ProcessedSeatReservationEvent> {
}
