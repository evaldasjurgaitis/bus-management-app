package ej.domain.aggregate;

import ej.domain.command.CustomerCommand;
import ej.domain.command.CustomerCreateCommand;
import ej.domain.command.CustomerUpdateCommand;
import ej.domain.event.customer.CustomerCreatedEvent;
import ej.domain.event.customer.CustomerUpdatedEvent;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class Customer extends ReflectiveMutableCommandProcessingAggregate<Customer, CustomerCommand> {

    private String name;
    private String surname;
    private String phone;
    private String email;

    public List<Event> process(CustomerCreateCommand cmd) {
        return EventUtil.events(new CustomerCreatedEvent(cmd));
    }

    public void apply(CustomerCreatedEvent event) {
        this.name = event.getName();
        this.surname = event.getSurname();
        this.phone = event.getPhone();
        this.email = event.getEmail();
    }

    public List<Event> process(CustomerUpdateCommand cmd) {
        return EventUtil.events(new CustomerUpdatedEvent(cmd));
    }

    public void apply(CustomerUpdatedEvent event) {
        this.name = event.getName();
        this.surname = event.getSurname();
        this.phone = event.getPhone();
        this.email = event.getEmail();
    }

}
