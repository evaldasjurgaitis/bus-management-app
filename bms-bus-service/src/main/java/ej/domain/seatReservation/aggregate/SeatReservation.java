package ej.domain.seatReservation.aggregate;

import ej.domain.seatReservation.event.seatReservation.SeatReservedEvent;
import ej.domain.seatReservation.event.seatReservation.SeatUnreservedEvent;
import ej.domain.seatReservation.command.SeatReservationCommand;
import ej.domain.seatReservation.command.SeatReserveCommand;
import ej.domain.seatReservation.command.SeatUnreservedCommand;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class SeatReservation extends ReflectiveMutableCommandProcessingAggregate<SeatReservation, SeatReservationCommand> {

    private String bookingItemId;
    private List<Seat> seats;

    public List<Event> process(SeatReserveCommand cmd) {
        return EventUtil.events(new SeatReservedEvent(cmd));
    }

    public void apply(SeatReservedEvent event) {
        this.bookingItemId = event.getBookingItemId();
        //reserveSeat(new Seat(UUID.randomUUID().toString()));
    }

    public List<Event> process(SeatUnreservedCommand cmd) {
        return EventUtil.events(new SeatUnreservedEvent(cmd));
    }

    public void apply(SeatUnreservedEvent event) {
        this.bookingItemId = event.getBookingItemId();
        unreserveSeat(event.getBookingItemId());
    }

    public void reserveSeat(Seat seat) {
        this.seats.add(seat);
    }

    public void unreserveSeat(String seatItemId) {
        this.seats.removeIf(i -> i.getSeatItemId().equals(seatItemId));
    }

}