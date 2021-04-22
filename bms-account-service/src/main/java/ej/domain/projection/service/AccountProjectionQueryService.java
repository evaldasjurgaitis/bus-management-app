package ej.domain.projection.service;

import ej.domain.exception.ResourceNotFoundException;
import ej.domain.projection.entity.AccountProjection;
import ej.domain.projection.repository.AccountProjectionRepository;

import java.util.List;

public class AccountProjectionQueryService {

    private final AccountProjectionRepository accountProjectionRepository;

    public AccountProjectionQueryService(AccountProjectionRepository accountProjectionRepository) {
        this.accountProjectionRepository = accountProjectionRepository;
    }

    public AccountProjection findById(String aggregateId) {
        return accountProjectionRepository.findById(aggregateId).orElseThrow(ResourceNotFoundException::new);
    }

    public List<AccountProjection> findAll() {
        return (List<AccountProjection>) accountProjectionRepository.findAll();
    }

}
