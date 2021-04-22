package ej.domain.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.aggregate.Customer;
import ej.domain.command.CustomerCommand;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedCustomerEvent;
import io.eventuate.AggregateRepository;

//@EventSubscriber(id = "customerEventHandlers")
public class CustomerProcessManager extends IdempotentEventHandler<ProcessedCustomerEvent> {

    private final AggregateRepository<Customer, CustomerCommand> customerRepository;

    public CustomerProcessManager(ProcessedEventService<ProcessedCustomerEvent> processedEventService, TransactionExecutor transactionExecutor, AggregateRepository<Customer, CustomerCommand> customerRepository) {
        super(processedEventService, ProcessedCustomerEvent.class, transactionExecutor);
        this.customerRepository = customerRepository;
    }
}
