package ej.domain.event.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingItemRemovedEvent extends BookingEvent {

    private String bookingItemId;

}
