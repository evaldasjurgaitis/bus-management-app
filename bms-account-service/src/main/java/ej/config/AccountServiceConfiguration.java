package ej.config;

import ej.domain.aggregate.Account;
import ej.domain.command.AccountCommand;
import ej.domain.process.AccountProcessManager;
import ej.domain.projection.process.AccountProjectionProcessManager;
import ej.domain.projection.repository.AccountProjectionRepository;
import ej.domain.projection.service.AccountProjectionQueryService;
import ej.domain.projection.service.AccountProjectionService;
import ej.domain.service.AccountService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.repository.ProcessedAccountEventRepository;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@EnableEventHandlers
@Import({EventuateDriverConfiguration.class, PersistenceAccountServiceConfiguration.class})
public class AccountServiceConfiguration {

    @Bean
    public AggregateRepository<Account, AccountCommand> accountAggregateRepository(EventuateAggregateStore aggregateStore) {
        return new AggregateRepository<>(Account.class, aggregateStore);
    }

    @Bean
    public AccountService accountService(AggregateRepository<Account, AccountCommand> accountAggregateRepository) {
        return new AccountService(accountAggregateRepository);
    }

    @Bean
    public AccountProjectionQueryService accountProjectionQueryService(AccountProjectionRepository accountProjectionRepository) {
        return new AccountProjectionQueryService(accountProjectionRepository);
    }

    @Bean
    public AccountProjectionService accountProjectionService(AccountProjectionRepository accountProjectionRepository) {
        return new AccountProjectionService(accountProjectionRepository);
    }

    @Bean
    public ProcessedEventService processedEventService(ProcessedAccountEventRepository processedAccountEventRepository) {
        return new ProcessedEventService(processedAccountEventRepository);
    }

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }

    @Bean
    public TransactionExecutor transactionExecutor() {
        return new TransactionExecutor();
    }

    @Bean
    public AccountProcessManager accountProcessManager(
            ProcessedEventService processedEventService,
            TransactionExecutor transactionExecutor,
            AggregateRepository<Account, AccountCommand> accountAggregateRepository,
            AccountProjectionQueryService accountProjectionQueryService
    ) {
        return new AccountProcessManager(
                processedEventService,
                transactionExecutor,
                accountAggregateRepository,
                accountProjectionQueryService
        );
    }

    @Bean
    public AccountProjectionProcessManager accountProjectionProcessManager(
            ProcessedEventService processedEventService,
            TransactionExecutor transactionExecutor,
            AccountProjectionService accountProjectionService,
            AccountProjectionQueryService accountProjectionQueryService
    ) {
        return new AccountProjectionProcessManager(
                processedEventService,
                transactionExecutor,
                accountProjectionService,
                accountProjectionQueryService
        );
    }

}
