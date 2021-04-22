package ej.config;

import ej.idempotent.ProcessedEventEntity;
import ej.idempotent.ProcessedEventService;
import io.eventuate.Event;
import io.eventuate.EventEnvelope;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

@Log4j2
public abstract class IdempotentEventHandler<T extends ProcessedEventEntity> {

    private final ProcessedEventService<T> processedEventService;

    private final Class<T> processedEntityType;

    private final TransactionExecutor transactionExecutor;

    public IdempotentEventHandler(ProcessedEventService<T> processedEventService, Class<T> processedEntityType, TransactionExecutor transactionExecutor) {
        this.processedEventService = processedEventService;
        this.processedEntityType = processedEntityType;
        this.transactionExecutor = transactionExecutor;
    }

    private T getInstanceOf(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleEvent(EventEnvelope<? extends Event> eventEnvelope, Consumer<Event> consumer) {
        transactionExecutor.execute(() -> {
            String eventId = eventEnvelope.getEntityId();
            if (processedEventService.findAllByEventId(eventId).size() == 0) {
                logInfoEvent(eventEnvelope);
                T processedEvent = getInstanceOf(processedEntityType);
                processedEvent.setEventId(eventEnvelope.getEntityId());
                processedEventService.save(processedEvent);
                consumer.accept(eventEnvelope.getEvent());
            } else {
                log.warn("Duplicated event skipped with id {}", eventId);
            }
        });

    }

    void logInfoEvent(EventEnvelope eventEnvelope) {
        log.info("Handling {} for aggregate {}", eventEnvelope.getEvent(), eventEnvelope.getEntityId());
    }

}