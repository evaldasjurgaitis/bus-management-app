package ej.api;

import ej.api.request.CustomerCreateRequest;
import ej.api.request.UpdateCustomerRequest;
import ej.api.response.CustomerResponse;
import ej.domain.exception.ResourceNotFoundException;
import ej.domain.projection.service.CustomerProjectionQueryService;
import ej.domain.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerProjectionQueryService customerProjectionQueryService;

    public CustomerController(CustomerService customerService, CustomerProjectionQueryService customerProjectionQueryService) {
        this.customerService = customerService;
        this.customerProjectionQueryService = customerProjectionQueryService;
    }

    @PostMapping
    public CompletableFuture<CustomerResponse> create(@RequestBody CustomerCreateRequest request) {
        return customerService.createCustomer(request)
                .thenApply(e -> CustomerResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    @PutMapping
    public CompletableFuture<CustomerResponse> update(@RequestBody UpdateCustomerRequest request) {
        return customerService.updateCustomer(request)
                .thenApply(e -> CustomerResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    @GetMapping("/{id}")
    public CustomerResponse get(@PathVariable String id) {
        return Optional.ofNullable(CustomerResponse.fromProjection(customerProjectionQueryService.findById(id)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return customerProjectionQueryService.findAll().stream()
                .map(CustomerResponse::fromProjection)
                .collect(Collectors.toList());
    }

}
