package ej.domain.projection.service;

import ej.domain.exception.ResourceNotFoundException;
import ej.domain.projection.entity.CustomerProjection;
import ej.domain.projection.repository.CustomerProjectionRepository;

import java.util.List;

public class CustomerProjectionQueryService {

    private CustomerProjectionRepository customerProjectionRepository;

    public CustomerProjectionQueryService(CustomerProjectionRepository customerProjectionRepository) {
        this.customerProjectionRepository = customerProjectionRepository;
    }

    public CustomerProjection findById(String aggregateId) {
        return customerProjectionRepository.findById(aggregateId).orElseThrow(ResourceNotFoundException::new);
    }

    public List<CustomerProjection> findAll() {
        return (List<CustomerProjection>) customerProjectionRepository.findAll();
    }

}
