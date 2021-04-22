package ej.idempotent;

import ej.idempotent.repository.ProcessedEventRepository;

import java.util.List;

public class ProcessedEventService<T extends ProcessedEventEntity> {

    private final ProcessedEventRepository<T> processedEventsRepository;

    public ProcessedEventService(ProcessedEventRepository<T> processedEventsRepository) {
        this.processedEventsRepository = processedEventsRepository;
    }

    public List<T> findAllByEventId(String eventId) {
       return processedEventsRepository.findAllByEventId(eventId);
    }

    public void save(T entity) {
        processedEventsRepository.save(entity);
    }

}


