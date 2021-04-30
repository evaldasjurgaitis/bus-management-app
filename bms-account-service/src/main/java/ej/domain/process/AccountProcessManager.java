package ej.domain.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.aggregate.Account;
import ej.domain.command.AccountCommand;
import ej.domain.command.AccountCreateCommand;
import ej.domain.command.ValidateCreditLimitCommand;
import ej.domain.event.booking.BasketConfirmedEvent;
import ej.domain.event.customer.CustomerCreatedEvent;
import ej.domain.projection.entity.AccountProjection;
import ej.domain.projection.service.AccountProjectionQueryService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedAccountEvent;
import io.eventuate.AggregateRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import java.math.BigDecimal;

@EventSubscriber(id = "accountEventHandlers")
public class AccountProcessManager extends IdempotentEventHandler<ProcessedAccountEvent> {

    private final AggregateRepository<Account, AccountCommand> accountAggregateRepository;
    private final AccountProjectionQueryService accountProjectionQueryService;

    public AccountProcessManager(ProcessedEventService<ProcessedAccountEvent> processedEventService,
                                 TransactionExecutor transactionExecutor,
                                 AggregateRepository<Account, AccountCommand> accountAggregateRepository,
                                 AccountProjectionQueryService accountProjectionQueryService) {
        super(processedEventService, ProcessedAccountEvent.class, transactionExecutor);
        this.accountAggregateRepository = accountAggregateRepository;
        this.accountProjectionQueryService = accountProjectionQueryService;
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

    @EventHandlerMethod
    public void validateCreditLimit(DispatchedEvent<BasketConfirmedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BasketConfirmedEvent event = (BasketConfirmedEvent) e;
                AccountProjection accountProjection = accountProjectionQueryService.findByCustomerAggregateId(event.getCustomerAggregateId());

                ValidateCreditLimitCommand cmd = new ValidateCreditLimitCommand();
                cmd.setIsValidCreditLimit(accountProjection.isValidCreditLimit(BigDecimal.TEN));
                cmd.setBookingAggregateId(event.getBookingAggregateId());

                accountAggregateRepository.save(cmd);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
