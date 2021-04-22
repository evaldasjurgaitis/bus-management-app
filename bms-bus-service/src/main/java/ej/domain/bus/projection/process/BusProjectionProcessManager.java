package ej.domain.bus.projection.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.bus.projection.service.BusProjectionService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedBusProjectionEvent;
import io.eventuate.EventSubscriber;

//@EventSubscriber(id = "busProjectionEventHandlers")
public class BusProjectionProcessManager extends IdempotentEventHandler<ProcessedBusProjectionEvent> {

    private final BusProjectionService busProjectionService;

    public BusProjectionProcessManager(ProcessedEventService processedEventService,
                                       TransactionExecutor transactionExecutor,
                                       BusProjectionService busProjectionService) {
        super(processedEventService, ProcessedBusProjectionEvent.class, transactionExecutor);
        this.busProjectionService = busProjectionService;
    }


}
