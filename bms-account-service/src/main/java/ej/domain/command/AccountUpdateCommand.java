package ej.domain.command;

import ej.api.request.AccountUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateCommand implements AccountCommand {

    private String name;

    public AccountUpdateCommand(AccountUpdateRequest accountUpdateRequest) {
        this.name = accountUpdateRequest.getName();
    }

}
