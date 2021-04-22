package ej.api;

import ej.api.request.AccountCreateRequest;
import ej.api.request.AccountUpdateRequest;
import ej.api.response.AccountResponse;
import ej.domain.exception.ResourceNotFoundException;
import ej.domain.projection.service.AccountProjectionQueryService;
import ej.domain.service.AccountService;
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
@RequestMapping(path = "/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountProjectionQueryService accountProjectionQueryService;

    public AccountController(AccountService accountService, AccountProjectionQueryService accountProjectionQueryService) {
        this.accountService = accountService;
        this.accountProjectionQueryService = accountProjectionQueryService;
    }

    @PostMapping
    public CompletableFuture<AccountResponse> create(@RequestBody AccountCreateRequest request) {
        return accountService.createAccount(request)
                .thenApply(e -> AccountResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    @PutMapping
    public CompletableFuture<AccountResponse> update(@RequestBody AccountUpdateRequest request) {
        return accountService.updateAccount(request)
                .thenApply(e -> AccountResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable String id) {
        return Optional.ofNullable(AccountResponse.fromProjection(accountProjectionQueryService.findById(id)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping
    public List<AccountResponse> getAll() {
        return accountProjectionQueryService.findAll().stream()
                .map(AccountResponse::fromProjection)
                .collect(Collectors.toList());
    }

}
