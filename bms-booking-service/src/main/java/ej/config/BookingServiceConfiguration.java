package ej.config;

import ej.domain.aggregate.Booking;
import ej.domain.command.BookingCommand;
import ej.domain.projection.process.BookingProjectionProcessManager;
import ej.domain.projection.repository.BookingProjectionRepository;
import ej.domain.projection.service.BookingProjectionQueryService;
import ej.domain.projection.service.BookingProjectionService;
import ej.domain.service.BookingService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.repository.ProcessedBookingEventRepository;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.local.java.spring.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableEventHandlers
@Import({EventuateDriverConfiguration.class, PersistenceBookingServiceConfiguration.class})
public class BookingServiceConfiguration {

    @Bean
    public AggregateRepository<Booking, BookingCommand> bookingRepository(EventuateAggregateStore eventStore) {
        return new AggregateRepository<>(Booking.class, eventStore);
    }

    @Bean
    public BookingService bookingService(AggregateRepository<Booking, BookingCommand> bookingRepository) {
        return new BookingService(bookingRepository);
    }

    @Bean
    public BookingProjectionQueryService bookingProjectionQueryService(BookingProjectionRepository bookingProjectionRepository) {
        return new BookingProjectionQueryService(bookingProjectionRepository);
    }

    @Bean
    public BookingProjectionService bookingProjectionService(BookingProjectionRepository bookingProjectionRepository) {
        return new BookingProjectionService(bookingProjectionRepository);
    }

    @Bean
    public ProcessedEventService processedEventService(ProcessedBookingEventRepository processedCustomerEventRepository) {
        return new ProcessedEventService(processedCustomerEventRepository);
    }

//    @Bean
//    public HttpMessageConverters bookingConverters() {
//        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
//        return new HttpMessageConverters(additional);
//    }

    @Bean
    public TransactionExecutor transactionExecutor() {
        return new TransactionExecutor();
    }

//    @Bean
//    public BookingProcessManager customerProcessManager(
//            ProcessedEventService processedEventService,
//            TransactionExecutor transactionExecutor,
//            AggregateRepository<Booking, BookingCommand> bookingAggregateRepository
//    ) {
//        return new BookingProcessManager(
//                processedEventService,
//                transactionExecutor,
//                bookingAggregateRepository
//        );
//    }

    @Bean
    public BookingProjectionProcessManager bookingProjectionProcessManager(
            ProcessedEventService processedEventService,
            TransactionExecutor transactionExecutor,
            BookingProjectionService bookingProjectionService,
            BookingProjectionQueryService bookingProjectionQueryService
    ) {
        return new BookingProjectionProcessManager(
                processedEventService,
                transactionExecutor,
                bookingProjectionService,
                bookingProjectionQueryService
        );
    }

}
