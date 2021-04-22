package ej.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    private String customerAggregateId;
    private String name;
    private String surname;
    private String phone;
    private String email;

}
