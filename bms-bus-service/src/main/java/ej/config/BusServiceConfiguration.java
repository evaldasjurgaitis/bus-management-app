package ej.config;

import ej.domain.bus.aggregate.Bus;
import ej.domain.bus.command.BusCommand;
import ej.domain.bus.projection.process.BusProjectionProcessManager;
import ej.domain.bus.projection.repository.BusProjectionRepository;
import ej.domain.bus.projection.service.BusProjectionQueryService;
import ej.domain.bus.projection.service.BusProjectionService;
import ej.domain.bus.service.BusService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.repository.ProcessedBusEventRepository;
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

//@Configuration
//@EnableEventHandlers
//@Import({EventuateDriverConfiguration.class, PersistenceBusServiceConfiguration.class})
public class BusServiceConfiguration {

//    @Bean
//    public AggregateRepository<Bus, BusCommand> busRepository(EventuateAggregateStore eventStore) {
//        return new AggregateRepository<>(Bus.class, eventStore);
//    }
//
//    @Bean
//    public BusService busService(AggregateRepository<Bus, BusCommand> busRepository) {
//        return new BusService(busRepository);
//    }
//
//    @Bean
//    public BusProjectionQueryService busProjectionQueryService(BusProjectionRepository busProjectionRepository) {
//        return new BusProjectionQueryService(busProjectionRepository);
//    }
//
//    @Bean
//    public BusProjectionService busProjectionService(BusProjectionRepository busProjectionRepository) {
//        return new BusProjectionService(busProjectionRepository);
//    }
//
////    @Bean
////    public ProcessedEventService processedEventService(ProcessedBusEventRepository processedBusEventRepository) {
////        return new ProcessedEventService(processedBusEventRepository);
////    }
////
////    @Bean
////    public HttpMessageConverters busConverters() {
////        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
////        return new HttpMessageConverters(additional);
////    }
//
////    @Bean
////    public TransactionExecutor transactionExecutor() {
////        return new TransactionExecutor();
////    }
//
////    @Bean
////    public BusProcessManager busProcessManager(
////            ProcessedEventService processedEventService,
////            TransactionExecutor transactionExecutor,
////            AggregateRepository<Bus, BusCommand> busCommandAggregateRepository
////    ) {
////        return new BusProcessManager(
////                processedEventService,
////                transactionExecutor,
////                busCommandAggregateRepository
////        );
////    }
//
//    @Bean
//    public BusProjectionProcessManager busProjectionProcessManager(
//            ProcessedEventService processedEventService,
//            TransactionExecutor transactionExecutor,
//            BusProjectionService busProjectionService
//    ) {
//        return new BusProjectionProcessManager(
//                processedEventService,
//                transactionExecutor,
//                busProjectionService
//        );
//    }

}
