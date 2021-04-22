package ej.domain.command;

import ej.api.request.UpdateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateCommand implements CustomerCommand {

    private String name;
    private String surname;
    private String phone;
    private String email;

    public CustomerUpdateCommand(UpdateCustomerRequest updateCustomerRequest) {
        this.name = updateCustomerRequest.getName();
        this.surname = updateCustomerRequest.getSurname();
        this.phone = updateCustomerRequest.getPhone();
        this.email = updateCustomerRequest.getEmail();
    }

}
