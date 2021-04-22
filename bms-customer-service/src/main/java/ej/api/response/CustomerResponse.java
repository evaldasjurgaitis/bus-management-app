package ej.api.response;

import ej.domain.aggregate.Customer;
import ej.domain.projection.entity.CustomerProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponse {

    private String aggregateId;
    private String name;
    private String surname;
    private String phone;
    private String email;

    public static CustomerResponse fromDomain(Customer customer, String aggregateId) {
        return CustomerResponse.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .aggregateId(aggregateId).build();
    }

    public static CustomerResponse fromProjection(CustomerProjection customerProjection) {
        return CustomerResponse.builder()
                .name(customerProjection.getName())
                .surname(customerProjection.getSurname())
                .phone(customerProjection.getPhone())
                .email(customerProjection.getEmail())
                .aggregateId(customerProjection.getAggregateId()).build();
    }

}
