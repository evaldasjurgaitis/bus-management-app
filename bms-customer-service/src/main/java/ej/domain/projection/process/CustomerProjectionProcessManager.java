package ej.domain.projection.process;

import ej.config.TransactionExecutor;
import ej.domain.event.customer.CustomerCreatedEvent;
import ej.domain.event.customer.CustomerUpdatedEvent;
import ej.domain.projection.entity.CustomerProjection;
import ej.domain.projection.service.CustomerProjectionService;
import ej.config.IdempotentEventHandler;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedCustomerProjectionEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import java.time.ZonedDateTime;

@EventSubscriber(id = "customerProjectionEventHandlers")
public class CustomerProjectionProcessManager extends IdempotentEventHandler<ProcessedCustomerProjectionEvent> {

    private final CustomerProjectionService customerProjectionService;

    public CustomerProjectionProcessManager(ProcessedEventService processedEventService, TransactionExecutor transactionExecutor, CustomerProjectionService customerProjectionService) {
        super(processedEventService, ProcessedCustomerProjectionEvent.class, transactionExecutor);
        this.customerProjectionService = customerProjectionService;
    }

    @EventHandlerMethod
    public void createdCustomer(DispatchedEvent<CustomerCreatedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                CustomerCreatedEvent event = (CustomerCreatedEvent) e;

                CustomerProjection customerProjection = new CustomerProjection();
                customerProjection.setAggregateId(ee.getEntityId());
                customerProjection.setName(event.getName());
                customerProjection.setSurname(event.getName());
                customerProjection.setEmail(event.getEmail());
                customerProjection.setPhone(event.getPhone());
                customerProjection.setPhone(event.getPhone());
                customerProjection.setTime(ZonedDateTime.now());

                customerProjectionService.save(customerProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void updatedCustomer(DispatchedEvent<CustomerUpdatedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                CustomerUpdatedEvent event = (CustomerUpdatedEvent) e;
                CustomerProjection customerProjection = new CustomerProjection();
                customerProjection.setAggregateId(ee.getEntityId());
                customerProjection.setName(event.getName());
                customerProjection.setSurname(event.getName());
                customerProjection.setEmail(event.getEmail());
                customerProjection.setPhone(event.getPhone());
                customerProjection.setTime(ZonedDateTime.now());
                customerProjectionService.save(customerProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
