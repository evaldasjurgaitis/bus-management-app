package ej.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passenger {

    private String name;
    private String surname;
    private String phone;

    public static Passenger fromDomain(Passenger passenger) {
        return Passenger.builder()
                .name(passenger.getName())
                .surname(passenger.getSurname())
                .phone(passenger.getPhone()).build();
    }

    public static Passenger fromProjection(ej.domain.projection.embeddable.Passenger passenger) {
        return Passenger.builder()
                .name(passenger.getName())
                .surname(passenger.getSurname())
                .phone(passenger.getPhone()).build();
    }

}
