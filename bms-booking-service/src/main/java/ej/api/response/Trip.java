package ej.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public static Trip fromDomain(Trip trip) {
        return Trip.builder()
                .id(trip.getId())
                .arrivalTime(trip.getArrivalTime())
                .departureTime(trip.getDepartureTime())
                .travelTime(trip.getTravelTime())
                .distance(trip.getDistance())
                .arrivalFrom(trip.getArrivalFrom())
                .departureTo(trip.getDepartureTo())
                .build();
    }

    public static Trip fromProjection(ej.domain.projection.embeddable.Trip trip) {
        return Trip.builder()
                .id(trip.getId())
                .arrivalTime(trip.getArrivalTime())
                .departureTime(trip.getDepartureTime())
                .travelTime(trip.getTravelTime())
                .distance(trip.getDistance())
                .arrivalFrom(trip.getArrivalFrom())
                .departureTo(trip.getDepartureTo()).build();
    }

}
