package ej.domain.projection.repository;

import ej.domain.projection.entity.CustomerProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CustomerProjectionRepository extends CrudRepository<CustomerProjection, String> {
}
