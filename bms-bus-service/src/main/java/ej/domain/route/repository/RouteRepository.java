package ej.domain.route.repository;

import ej.domain.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findAllByArrivalFrom_NameAndAndDepartureTo_Name(String arrivalFrom, String departureTo);

}
