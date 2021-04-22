package ej.domain.command;

import ej.api.request.AccountCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateCommand implements AccountCommand {

    private String name;
    private String customerAggregateId;

    public AccountCreateCommand(AccountCreateRequest accountCreateRequest) {
        this.name = accountCreateRequest.getName();
        this.customerAggregateId = accountCreateRequest.getCustomerAggregateId();
    }

}
