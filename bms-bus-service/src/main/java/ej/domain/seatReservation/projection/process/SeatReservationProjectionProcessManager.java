package ej.domain.seatReservation.projection.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.seatReservation.event.seatReservation.SeatReservedEvent;
import ej.domain.seatReservation.projection.entity.SeatReservationProjection;
import ej.domain.seatReservation.projection.service.SeatReservationProjectionQueryService;
import ej.domain.seatReservation.projection.service.SeatReservationProjectionService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedSeatReservationProjectionEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.log4j.Log4j2;

import java.time.ZonedDateTime;

@EventSubscriber(id = "seatReservationProjectionEventHandlers")
@Log4j2
public class SeatReservationProjectionProcessManager extends IdempotentEventHandler<ProcessedSeatReservationProjectionEvent> {

    private final SeatReservationProjectionService seatReservationProjectionService;
    private final SeatReservationProjectionQueryService seatReservationProjectionQueryService;

    public SeatReservationProjectionProcessManager(ProcessedEventService processedEventService,
                                                   TransactionExecutor transactionExecutor,
                                                   SeatReservationProjectionService seatReservationProjectionService,
                                                   SeatReservationProjectionQueryService seatReservationProjectionQueryService) {
        super(processedEventService, ProcessedSeatReservationProjectionEvent.class, transactionExecutor);
        this.seatReservationProjectionService = seatReservationProjectionService;
        this.seatReservationProjectionQueryService = seatReservationProjectionQueryService;
    }

    @EventHandlerMethod
    public void seatReserved(DispatchedEvent<SeatReservedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                SeatReservedEvent event = (SeatReservedEvent) e;

                SeatReservationProjection seatReservationProjection = new SeatReservationProjection();
                seatReservationProjection.setAggregateId(ee.getEntityId());
                seatReservationProjection.setCreatedTime(ZonedDateTime.now());

                seatReservationProjectionService.save(seatReservationProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}