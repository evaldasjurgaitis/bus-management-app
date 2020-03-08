package ej.api;

import ej.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private String surname;

    public static CustomerResponse convertToDTO(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .build();
    }

    public static List<CustomerResponse> convertToDTO(List<Customer> customers) {
        return customers.stream()
                .map(CustomerResponse::convertToDTO)
                .collect(Collectors.toList());
    }
}
