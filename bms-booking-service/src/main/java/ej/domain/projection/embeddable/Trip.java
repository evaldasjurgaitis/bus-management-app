package ej.domain.projection.embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Trip {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;
    private Double travelTime;
    private Double distance;
    private String arrivalFrom;
    private String departureTo;

    public Trip (Trip trip) {
        this.id = trip.getId();
        this.arrivalTime = trip.getArrivalTime();
        this.departureTime = trip.getDepartureTime();
        this.travelTime = trip.getTravelTime();
        this.distance = trip.getDistance();
        this.arrivalFrom = trip.getArrivalFrom();
        this.departureTo = trip.getDepartureTo();
    }

}
