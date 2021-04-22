package ej.domain.seatReservation.projection.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Entity
@Table(name = "seat_projection")
public class SeatProjection {

    @Id
    @Column(name = "seat_item_id", insertable = false, updatable = false)
    @NotNull
    private String seatItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_reservation_aggregate_id")
    @JsonBackReference
    private SeatReservationProjection seatReservationProjection;

}
