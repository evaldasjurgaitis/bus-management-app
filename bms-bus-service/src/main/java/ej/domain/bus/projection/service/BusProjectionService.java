package ej.domain.bus.projection.service;

import ej.domain.bus.projection.entity.BusProjection;
import ej.domain.bus.projection.repository.BusProjectionRepository;

public class BusProjectionService {

    private final BusProjectionRepository busProjectionRepository;

    public BusProjectionService(BusProjectionRepository busProjectionRepository) {
        this.busProjectionRepository = busProjectionRepository;
    }

    public void save(BusProjection entity) {
        busProjectionRepository.save(entity);
    }

}
