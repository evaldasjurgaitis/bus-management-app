package ej.domain.projection.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Passenger {

    private String name;
    private String surname;
    private String phone;

    public Passenger(Passenger passenger) {
        this.name = passenger.getName();
        this.surname = passenger.getSurname();
        this.phone = passenger.getPhone();
    }
}
