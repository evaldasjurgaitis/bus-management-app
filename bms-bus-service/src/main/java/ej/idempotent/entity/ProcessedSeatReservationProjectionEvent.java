package ej.idempotent.entity;

import ej.idempotent.ProcessedEventEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "processed_seat_reservation_projection_event")
public class ProcessedSeatReservationProjectionEvent implements ProcessedEventEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }


}
