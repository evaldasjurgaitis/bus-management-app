package ej.domain.command;

import ej.api.request.CustomerCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateCommand implements CustomerCommand {

    private String name;
    private String surname;
    private String phone;
    private String email;

    public CustomerCreateCommand(CustomerCreateRequest customerCreateRequest) {
        this.name = customerCreateRequest.getName();
        this.surname = customerCreateRequest.getSurname();
        this.phone = customerCreateRequest.getPhone();
        this.email = customerCreateRequest.getEmail();
    }

}
