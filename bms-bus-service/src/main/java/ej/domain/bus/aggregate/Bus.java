package ej.domain.bus.aggregate;

import ej.domain.bus.command.BusCommand;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class Bus extends ReflectiveMutableCommandProcessingAggregate<Bus, BusCommand> {

    private Long id;

    private String name;

    private Long seatsCount;

    //private List<Schedule> timetable;

}