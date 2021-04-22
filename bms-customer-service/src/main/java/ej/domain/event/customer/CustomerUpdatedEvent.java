package ej.domain.event.customer;

import ej.domain.command.CustomerUpdateCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerUpdatedEvent extends CustomerEvent {

    private String name;
    private String surname;
    private String phone;
    private String email;

    public CustomerUpdatedEvent(CustomerUpdateCommand cmd) {
        this.name = cmd.getName();
        this.surname = cmd.getSurname();
        this.phone = cmd.getPhone();
        this.email = cmd.getPhone();
    }

}
