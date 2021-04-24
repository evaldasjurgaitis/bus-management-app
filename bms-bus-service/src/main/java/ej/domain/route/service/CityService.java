package ej.domain.route.service;

import ej.api.dto.City;
import ej.domain.route.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    public final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities() {
        return cityRepository.findAll().stream()
                .map(City::new)
                .collect(Collectors.toList());
    }

}
