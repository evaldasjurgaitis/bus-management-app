package ej.domain.service;

import ej.api.request.CustomerCreateRequest;
import ej.api.request.UpdateCustomerRequest;
import ej.domain.command.CustomerCommand;
import ej.domain.command.CustomerCreateCommand;
import ej.domain.command.CustomerUpdateCommand;
import ej.domain.aggregate.Customer;
import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

public class CustomerService {

    private final AggregateRepository<Customer, CustomerCommand> customerRepository;

    public CustomerService(AggregateRepository<Customer, CustomerCommand> customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<Customer>> createCustomer(CustomerCreateRequest customerCreateRequest) {
        return customerRepository.save(new CustomerCreateCommand(customerCreateRequest));
    }

    public CompletableFuture<EntityWithIdAndVersion<Customer>> updateCustomer(UpdateCustomerRequest updateCustomerRequest) {
        return customerRepository.update(updateCustomerRequest.getCustomerAggregateId(), new CustomerUpdateCommand(updateCustomerRequest));
    }

}
