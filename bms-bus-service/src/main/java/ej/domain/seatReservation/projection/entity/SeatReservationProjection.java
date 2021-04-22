package ej.domain.seatReservation.projection.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "seat_reservation_projection")
public class SeatReservationProjection {

    @Id
    @Column(name = "aggregate_id", nullable = false)
    @NotNull
    private String aggregateId;

    @Column(name = "created_time", nullable = false)
    @NotNull
    private ZonedDateTime createdTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seatReservationProjection", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SeatProjection> seats;

    public void removeItem(String seatItemId) {
        seats.removeIf(i -> i.getSeatItemId().equals(seatItemId));
    }

}
