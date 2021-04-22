package ej.domain.event.customer;

import ej.domain.command.CustomerCreateCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomerCreatedEvent extends CustomerEvent {

    private String name;
    private String surname;
    private String phone;
    private String email;

    public CustomerCreatedEvent(CustomerCreateCommand customerCreateCommand) {
        this.name = customerCreateCommand.getName();
        this.surname = customerCreateCommand.getSurname();
        this.phone = customerCreateCommand.getPhone();
        this.email = customerCreateCommand.getPhone();
    }

}
