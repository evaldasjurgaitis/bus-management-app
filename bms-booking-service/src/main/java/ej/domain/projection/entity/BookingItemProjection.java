package ej.domain.projection.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ej.domain.projection.embeddable.Passenger;
import ej.domain.projection.embeddable.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "booking_item_projection")
public class BookingItemProjection {

    @Id
    @Column(name = "booking_item_id", insertable = false, updatable = false)
    @NotNull
    private String bookingItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_aggregate_id")
    @JsonBackReference
    private BookingProjection bookingProjection;

    @Embedded
    private Passenger passenger;

    @Embedded
    private Trip trip;

}
