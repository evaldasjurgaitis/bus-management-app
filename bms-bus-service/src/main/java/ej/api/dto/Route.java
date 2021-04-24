package ej.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    private Long id;
    private Bus bus;
    private City arrivalFrom;
    private City departureTo;
    private List<Timetable> timetables;

    public Route(ej.domain.route.entity.Route route) {
        this.id = route.getId();
        this.bus = new Bus(route.getBus().getName(), route.getBus().getSeatsNo());
        this.arrivalFrom = new City(
                route.getArrivalFrom().getName(),
                route.getArrivalFrom().getLatitude(),
                route.getArrivalFrom().getLongitude()
        );
        this.departureTo = new City(
                route.getDepartureTo().getName(),
                route.getDepartureTo().getLatitude(),
                route.getDepartureTo().getLongitude()
        );
        this.timetables = route.getTimetables().stream()
                .map(Timetable::new)
                .collect(Collectors.toList());
    }

}
