package ej.domain.bus.projection.repository;

import ej.domain.bus.projection.entity.BusProjection;
import org.springframework.data.repository.CrudRepository;

public interface BusProjectionRepository extends CrudRepository<BusProjection, String> {
}
