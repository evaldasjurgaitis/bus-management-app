package ej.domain.event.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreatedEvent extends CustomerEvent {

    private String name;
    private String surname;
    private String phone;
    private String email;

}
