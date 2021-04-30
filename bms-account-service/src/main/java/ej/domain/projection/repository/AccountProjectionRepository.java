package ej.domain.projection.repository;

import ej.domain.projection.entity.AccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountProjectionRepository extends JpaRepository<AccountProjection, String> {

    Optional<AccountProjection> findByCustomerAggregateId(String customerAggregateId);

}
