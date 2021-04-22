package ej.domain.projection.service;

import ej.domain.projection.entity.CustomerProjection;
import ej.domain.projection.repository.CustomerProjectionRepository;

public class CustomerProjectionService {

    private final CustomerProjectionRepository customerProjectionRepository;

    public CustomerProjectionService(CustomerProjectionRepository customerProjectionRepository) {
        this.customerProjectionRepository = customerProjectionRepository;
    }

    public void save(CustomerProjection entity) {
        customerProjectionRepository.save(entity);
    }

}
