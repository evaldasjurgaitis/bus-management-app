package ej.domain.projection.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.event.account.AccountCreatedEvent;
import ej.domain.event.account.AccountUpdatedEvent;
import ej.domain.projection.entity.AccountProjection;
import ej.domain.projection.service.AccountProjectionService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedAccountProjectionEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@EventSubscriber(id = "accountProjectEventHandlers")
public class AccountProjectionProcessManager extends IdempotentEventHandler<ProcessedAccountProjectionEvent> {

    private final AccountProjectionService accountProjectionService;

    public AccountProjectionProcessManager(ProcessedEventService processedEventService, TransactionExecutor transactionExecutor, AccountProjectionService accountProjectionService) {
        super(processedEventService, ProcessedAccountProjectionEvent.class, transactionExecutor);
        this.accountProjectionService = accountProjectionService;
    }

    @EventHandlerMethod
    public void createdAccount(DispatchedEvent<AccountCreatedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                AccountCreatedEvent event = (AccountCreatedEvent) e;

                AccountProjection accountProjection = new AccountProjection();
                accountProjection.setAggregateId(ee.getEntityId());
                accountProjection.setName(event.getName());
                accountProjection.setCustomerAggregateId(event.getCustomerAggregateId());
                accountProjection.setCreditLimit(BigDecimal.ZERO);
                accountProjection.setTime(ZonedDateTime.now());

                accountProjectionService.save(accountProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void updatedAccount(DispatchedEvent<AccountUpdatedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                AccountUpdatedEvent event = (AccountUpdatedEvent) e;

                AccountProjection accountProjection = new AccountProjection();
                accountProjection.setAggregateId(ee.getEntityId());
                accountProjection.setName(event.getName());

                accountProjectionService.save(accountProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
