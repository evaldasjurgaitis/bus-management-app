package ej.domain.route.service;

import ej.api.dto.Route;
import ej.domain.route.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> searchRoutes(String arrivalFrom, String departureTo) {
        return routeRepository.findAllByArrivalFrom_NameAndAndDepartureTo_Name(arrivalFrom, departureTo).stream()
                .map(Route::new)
                .collect(Collectors.toList());
    }

}
