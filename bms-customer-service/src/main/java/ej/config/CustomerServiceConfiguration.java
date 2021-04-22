package ej.config;

import ej.domain.aggregate.Customer;
import ej.domain.process.CustomerProcessManager;
import ej.domain.projection.process.CustomerProjectionProcessManager;
import ej.domain.projection.repository.CustomerProjectionRepository;
import ej.domain.projection.service.CustomerProjectionQueryService;
import ej.domain.projection.service.CustomerProjectionService;
import ej.domain.service.CustomerService;
import ej.domain.command.CustomerCommand;
import ej.idempotent.repository.ProcessedCustomerEventRepository;
import ej.idempotent.ProcessedEventService;
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
@Import({EventuateDriverConfiguration.class, PersistenceCustomerServiceConfiguration.class})
public class CustomerServiceConfiguration {

    @Bean
    public AggregateRepository<Customer, CustomerCommand> customerRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(Customer.class, eventStore);
    }

    @Bean
    public CustomerService customerService(AggregateRepository<Customer, CustomerCommand> customerRepository) {
        return new CustomerService(customerRepository);
    }

    @Bean
    public CustomerProjectionQueryService customerProjectionQueryService(CustomerProjectionRepository customerProjectionRepository) {
        return new CustomerProjectionQueryService(customerProjectionRepository);
    }

    @Bean
    public CustomerProjectionService customerProjectionService(CustomerProjectionRepository customerProjectionRepository) {
        return new CustomerProjectionService(customerProjectionRepository);
    }

    @Bean
    public ProcessedEventService processedEventService(ProcessedCustomerEventRepository processedCustomerEventRepository) {
        return new ProcessedEventService(processedCustomerEventRepository);
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
    public CustomerProcessManager customerProcessManager(
            ProcessedEventService processedEventService,
            TransactionExecutor transactionExecutor,
            AggregateRepository<Customer, CustomerCommand> customerAggregateRepository
            ) {
        return new CustomerProcessManager(
                processedEventService,
                transactionExecutor,
                customerAggregateRepository
        );
    }

    @Bean
    public CustomerProjectionProcessManager customerProjectionProcessManager(
            ProcessedEventService processedEventService,
            TransactionExecutor transactionExecutor,
            CustomerProjectionService customerProjectionService
    ) {
        return new CustomerProjectionProcessManager(
                processedEventService,
                transactionExecutor,
                customerProjectionService
        );
    }

}
