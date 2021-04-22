package ej.domain.bus.projection.service;

import ej.domain.bus.exception.ResourceNotFoundException;
import ej.domain.bus.projection.repository.BusProjectionRepository;
import ej.domain.bus.projection.entity.BusProjection;

import java.util.List;

public class BusProjectionQueryService {

    private final BusProjectionRepository busProjectionRepository;

    public BusProjectionQueryService(BusProjectionRepository busProjectionRepository) {
        this.busProjectionRepository = busProjectionRepository;
    }

    public BusProjection findById(String aggregateId) {
        return busProjectionRepository.findById(aggregateId).orElseThrow(ResourceNotFoundException::new);
    }

    public List<BusProjection> findAll() {
        return (List<BusProjection>) busProjectionRepository.findAll();
    }

}
