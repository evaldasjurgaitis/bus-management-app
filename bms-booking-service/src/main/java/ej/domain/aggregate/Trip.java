package ej.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    private Long id;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private Double travelTime;
    private Double distance;
    private String arrivalFrom;
    private String departureTo;

}
