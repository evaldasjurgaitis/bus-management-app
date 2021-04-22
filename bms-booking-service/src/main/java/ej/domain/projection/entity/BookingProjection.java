package ej.domain.projection.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ej.domain.projection.embeddable.BookingState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "booking_projection")
public class BookingProjection {

    @Id
    @Column(name = "aggregate_id", nullable = false)
    @NotNull
    private String aggregateId;

    @Column(name = "created_time", nullable = false)
    @NotNull
    private ZonedDateTime createdTime;

    @Column(name = "booking_state", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private BookingState bookingState;

    @Column(name = "customer_aggregate_id", nullable = false)
    @NotNull
    private String customerAggregateId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingProjection", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BookingItemProjection> bookingItems;

    public void removeItem(String bookingItemId) {
        bookingItems.removeIf(i -> i.getBookingItemId().equals(bookingItemId));
    }

}
