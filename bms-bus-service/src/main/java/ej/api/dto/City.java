package ej.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private String name;
    private double latitude;
    private double longitude;

    public City(ej.domain.route.entity.City city) {
        this.name = city.getName();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
    }

}
