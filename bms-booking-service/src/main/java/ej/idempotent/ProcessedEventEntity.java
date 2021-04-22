package ej.idempotent;

public interface ProcessedEventEntity {

    Long getId();

    void setId(Long id);

    String getEventId();

    void setEventId(String eventId);
}
