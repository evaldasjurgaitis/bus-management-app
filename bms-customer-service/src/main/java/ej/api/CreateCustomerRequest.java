package ej.api;

import ej.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

    private String name;
    private String surname;

    public static Customer convertToEntity(CreateCustomerRequest createCustomerRequest) {
        return new Customer(createCustomerRequest.name, createCustomerRequest.surname);
    }

}
