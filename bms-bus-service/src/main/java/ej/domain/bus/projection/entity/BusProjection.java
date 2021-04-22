package ej.domain.bus.projection.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@NoArgsConstructor
@Data
@Entity
@Table(name = "bus_projection")
public class BusProjection {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "seatsNumber")
    private Long seatsNumber;

    @ElementCollection
    private Map<String, String> timetable;

}
