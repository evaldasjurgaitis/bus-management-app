package ej.domain.projection.service;

import ej.domain.projection.entity.AccountProjection;
import ej.domain.projection.repository.AccountProjectionRepository;

public class AccountProjectionService {

    private final AccountProjectionRepository accountProjectionRepository;

    public AccountProjectionService(AccountProjectionRepository accountProjectionRepository) {
        this.accountProjectionRepository = accountProjectionRepository;
    }

    public void save(AccountProjection entity) {
        accountProjectionRepository.save(entity);
    }

}
