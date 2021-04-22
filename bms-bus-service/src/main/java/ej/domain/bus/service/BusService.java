package ej.domain.bus.service;

import ej.domain.bus.aggregate.Bus;
import ej.domain.bus.command.BusCommand;
import io.eventuate.AggregateRepository;

public class BusService {

    private final AggregateRepository<Bus, BusCommand> busRepository;

    public BusService(AggregateRepository<Bus, BusCommand> busRepository) {
        this.busRepository = busRepository;
    }

}
