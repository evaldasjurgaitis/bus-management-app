package ej.domain.projection.repository;

import ej.domain.projection.entity.AccountProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AccountProjectionRepository extends CrudRepository<AccountProjection, String> {
}
