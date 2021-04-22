package ej.idempotent.repository;

import ej.idempotent.entity.ProcessedBusEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedBusEventRepository extends ProcessedEventRepository<ProcessedBusEvent> {
}
