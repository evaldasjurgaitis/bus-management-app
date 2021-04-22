package ej.domain.seatReservation.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.seatReservation.command.SeatReserveCommand;
import ej.domain.seatReservation.command.SeatUnreservedCommand;
import ej.domain.event.booking.BookingItemAddedEvent;
import ej.domain.event.booking.BookingItemRemovedEvent;
import ej.domain.seatReservation.service.SeatReservationService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedSeatReservationEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "seatReservationEventHandlers")
public class SeatReservationProcessManager extends IdempotentEventHandler<ProcessedSeatReservationEvent> {

    private final SeatReservationService seatReservationService;

    public SeatReservationProcessManager(ProcessedEventService<ProcessedSeatReservationEvent> processedEventService, TransactionExecutor transactionExecutor, SeatReservationService seatReservationService) {
        super(processedEventService, ProcessedSeatReservationEvent.class, transactionExecutor);
        this.seatReservationService = seatReservationService;
    }

    @EventHandlerMethod
    public void seatReserve(DispatchedEvent<BookingItemAddedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingItemAddedEvent event = (BookingItemAddedEvent) e;

                SeatReserveCommand cmd = new SeatReserveCommand();
                cmd.setBookingItemId(event.getBookingItemId());

                seatReservationService.createSeatReservation(null,cmd);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void seatUnReserve(DispatchedEvent<BookingItemRemovedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingItemRemovedEvent event = (BookingItemRemovedEvent) e;

                SeatUnreservedCommand cmd = new SeatUnreservedCommand();
                cmd.setBookingItemId(event.getBookingItemId());

                seatReservationService.updateSeatReservation(event.getBookingItemId(), cmd);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
