package ej.domain.service;

import ej.api.request.AccountCreateRequest;
import ej.api.request.AccountRefillCreditLimitRequest;
import ej.api.request.AccountUpdateRequest;
import ej.domain.aggregate.Account;
import ej.domain.command.AccountCommand;
import ej.domain.command.AccountCreateCommand;
import ej.domain.command.AccountUpdateCommand;
import ej.domain.command.RefillCreditLimitCommand;
import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

public class AccountService {

    private final AggregateRepository<Account, AccountCommand> accountRepository;

    public AccountService(AggregateRepository<Account, AccountCommand> accountRepository) {
        this.accountRepository = accountRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<Account>> createAccount(AccountCreateRequest accountCreateRequest) {
        return accountRepository.save(new AccountCreateCommand(accountCreateRequest));
    }

    public CompletableFuture<EntityWithIdAndVersion<Account>> updateAccount(String aggregateId, AccountUpdateRequest accountUpdateRequest) {
        return accountRepository.update(aggregateId, new AccountUpdateCommand(accountUpdateRequest));
    }

    public CompletableFuture<EntityWithIdAndVersion<Account>> refillCreditLimit(String aggregateId, AccountRefillCreditLimitRequest request) {
        return accountRepository.update(aggregateId, new RefillCreditLimitCommand(aggregateId, request.getAmount()));
    }

}
