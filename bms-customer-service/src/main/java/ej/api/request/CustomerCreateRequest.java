package ej.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateRequest {

    private String name;
    private String surname;
    private String phone;
    private String email;

}
