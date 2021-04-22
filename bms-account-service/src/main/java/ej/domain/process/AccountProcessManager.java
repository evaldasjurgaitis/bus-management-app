package ej.domain.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.aggregate.Account;
import ej.domain.command.AccountCommand;
import ej.domain.command.AccountCreateCommand;
import ej.domain.event.customer.CustomerCreatedEvent;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedAccountEvent;
import io.eventuate.AggregateRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "accountEventHandlers")
public class AccountProcessManager extends IdempotentEventHandler<ProcessedAccountEvent> {

    private final AggregateRepository<Account, AccountCommand> accountAggregateRepository;

    public AccountProcessManager(ProcessedEventService<ProcessedAccountEvent> processedEventService, TransactionExecutor transactionExecutor, AggregateRepository<Account, AccountCommand> accountAggregateRepository) {
        super(processedEventService, ProcessedAccountEvent.class, transactionExecutor);
        this.accountAggregateRepository = accountAggregateRepository;
    }

    @EventHandlerMethod
    public void createAccount(DispatchedEvent<CustomerCreatedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                CustomerCreatedEvent event = (CustomerCreatedEvent) e;

                AccountCreateCommand cmd = new AccountCreateCommand();
                cmd.setName(event.getName());
                cmd.setCustomerAggregateId(ee.getEntityId());

                accountAggregateRepository.save(cmd);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
