package ej.domain.event.account;

import ej.domain.command.AccountCreateCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedEvent extends AccountEvent {

    private String name;
    private String customerAggregateId;

    public AccountCreatedEvent(AccountCreateCommand cmd) {
        this.name = cmd.getName();
        this.customerAggregateId = cmd.getCustomerAggregateId();
    }

}
