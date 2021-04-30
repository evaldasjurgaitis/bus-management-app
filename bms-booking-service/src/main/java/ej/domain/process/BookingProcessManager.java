package ej.domain.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.aggregate.Booking;
import ej.domain.command.BookingCommand;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedBookingEvent;
import io.eventuate.AggregateRepository;
import io.eventuate.EventSubscriber;

//@EventSubscriber(id = "bookingEventHandlers")
//public class BookingProcessManager extends IdempotentEventHandler<ProcessedBookingEvent> {

//    private final AggregateRepository<Booking, BookingCommand> bookingAggregateRepository;
//
//    public BookingProcessManager(ProcessedEventService<ProcessedBookingEvent> processedEventService, TransactionExecutor transactionExecutor, AggregateRepository<Booking, BookingCommand> bookingAggregateRepository) {
//        super(processedEventService, ProcessedBookingEvent.class, transactionExecutor);
//        this.bookingAggregateRepository = bookingAggregateRepository;
//    }

//}
