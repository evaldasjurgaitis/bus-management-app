package ej.domain.aggregate;

import ej.domain.command.AccountCommand;
import ej.domain.command.AccountCreateCommand;
import ej.domain.command.AccountUpdateCommand;
import ej.domain.command.RefillCreditCommand;
import ej.domain.command.RefundPaymentCommand;
import ej.domain.command.TakePaymentCommand;
import ej.domain.command.ValidateCreditLimitCommand;
import ej.domain.event.account.AccountCreatedEvent;
import ej.domain.event.account.AccountCreditExceededEvent;
import ej.domain.event.account.AccountUpdatedEvent;
import ej.domain.event.account.RefilledCreditEvent;
import ej.domain.event.account.RefundedPaymentEvent;
import ej.domain.event.account.TakenPaymentEvent;
import ej.domain.event.account.ValidatedCreditLimitEvent;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class Account extends ReflectiveMutableCommandProcessingAggregate<Account, AccountCommand> {

    private String name;
    private BigDecimal creditLimit;
    private String customerAggregateId;

    public List<Event> process(AccountCreateCommand cmd) {
        return EventUtil.events(new AccountCreatedEvent(cmd));
    }

    public void apply(AccountCreatedEvent event) {
        this.name = event.getName();
        this.creditLimit = BigDecimal.ZERO;
        this.customerAggregateId = event.getCustomerAggregateId();
    }

    public List<Event> process(AccountUpdateCommand cmd) {
        return EventUtil.events(new AccountUpdatedEvent(cmd.getName()));
    }

    public void apply(AccountUpdatedEvent event) {
        this.name = event.getName();
    }

    public List<Event> process(RefillCreditCommand cmd) {
        return EventUtil.events(new RefilledCreditEvent(cmd.getCreditAmount()));
    }

    public void apply(RefilledCreditEvent event) {
        this.creditLimit = this.creditLimit.add(event.getCreditAmount());
    }

    public List<Event> process(TakePaymentCommand cmd) {
        return EventUtil.events(new TakenPaymentEvent(cmd.getAmountTotal(), cmd.getOrderAggregateId()));
    }

    public void apply(TakenPaymentEvent event) {
        this.creditLimit = this.creditLimit.subtract(event.getAmountTotal());
    }

    public List<Event> process(RefundPaymentCommand cmd) {
        return EventUtil.events(new RefundedPaymentEvent(cmd.getAmountTotal()));
    }

    public void apply(RefundedPaymentEvent event) {
        this.creditLimit = this.creditLimit.add(event.getAmountTotal());
    }

    public List<Event> process(ValidateCreditLimitCommand cmd) {
        if (this.creditLimit.equals(cmd.getAmountTotal()) || this.creditLimit.compareTo(cmd.getAmountTotal()) > 0) {
            return EventUtil.events(new ValidatedCreditLimitEvent());
        } else {
            return EventUtil.events(new AccountCreditExceededEvent(cmd.getOrderAggregateId()));
        }
    }

    public void apply(ValidatedCreditLimitEvent event) {
        // noop
    }

    public void apply(AccountCreditExceededEvent event) {
        // noop
    }

}
