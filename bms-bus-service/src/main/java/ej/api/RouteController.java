package ej.api;

import ej.api.dto.Route;
import ej.domain.route.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/search")
    public List<Route> getRoutes(@RequestParam String arrivalFrom, @RequestParam String departureTo) {
        return routeService.searchRoutes(arrivalFrom, departureTo);
    }

}
