package ej.config;

import ej.domain.seatReservation.aggregate.SeatReservation;
import ej.domain.seatReservation.command.SeatReservationCommand;
import ej.domain.seatReservation.process.SeatReservationProcessManager;
import ej.domain.seatReservation.projection.process.SeatReservationProjectionProcessManager;
import ej.domain.seatReservation.projection.repository.SeatReservationProjectionRepository;
import ej.domain.seatReservation.projection.service.SeatReservationProjectionQueryService;
import ej.domain.seatReservation.projection.service.SeatReservationProjectionService;
import ej.domain.seatReservation.service.SeatReservationService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.repository.ProcessedSeatReservationEventRepository;
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
@Import({EventuateDriverConfiguration.class, PersistenceBusServiceConfiguration.class})
public class SeatReservationServiceConfiguration {

    @Bean
    public AggregateRepository<SeatReservation, SeatReservationCommand> seatReservationAggregateRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(SeatReservation.class, eventStore);
    }

    @Bean
    public SeatReservationService seatReservationService(AggregateRepository<SeatReservation, SeatReservationCommand> seatReservationCommandAggregateRepository) {
        return new SeatReservationService(seatReservationCommandAggregateRepository);
    }

    @Bean
    public SeatReservationProjectionQueryService seatReservationProjectionQueryService(SeatReservationProjectionRepository seatReservationProjectionRepository) {
        return new SeatReservationProjectionQueryService(seatReservationProjectionRepository);
    }

    @Bean
    public SeatReservationProjectionService seatReservationProjectionService(SeatReservationProjectionRepository seatReservationProjectionRepository) {
        return new SeatReservationProjectionService(seatReservationProjectionRepository);
    }

    @Bean
    public ProcessedEventService processedEventService(ProcessedSeatReservationEventRepository processedSeatReservationEventRepository) {
        return new ProcessedEventService(processedSeatReservationEventRepository);
    }

    @Bean
    public HttpMessageConverters customerConverter() {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }

    @Bean
    public TransactionExecutor transactionExecutor() {
        return new TransactionExecutor();
    }

    @Bean
    public SeatReservationProcessManager seatReservationProcessManager(
            ProcessedEventService processedEventService,
            TransactionExecutor transactionExecutor,
            SeatReservationService seatReservationService
    ) {
        return new SeatReservationProcessManager(
                processedEventService,
                transactionExecutor,
                seatReservationService
        );
    }

    @Bean
    public SeatReservationProjectionProcessManager seatReservationProjectionProcessManager(
            ProcessedEventService processedEventService,
            TransactionExecutor transactionExecutor,
            SeatReservationProjectionService seatReservationProjectionService,
            SeatReservationProjectionQueryService seatReservationProjectionQueryService
    ) {
        return new SeatReservationProjectionProcessManager(
                processedEventService,
                transactionExecutor,
                seatReservationProjectionService,
                seatReservationProjectionQueryService
        );
    }

}
