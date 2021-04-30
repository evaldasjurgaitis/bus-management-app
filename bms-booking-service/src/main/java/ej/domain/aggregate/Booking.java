package ej.domain.aggregate;

import ej.domain.command.AddBookingItemCommand;
import ej.domain.command.BookingCommand;
import ej.domain.command.CancelBookingCommand;
import ej.domain.command.ConfirmBasketCommand;
import ej.domain.command.ConfirmBookingCommand;
import ej.domain.command.RejectBookingCommand;
import ej.domain.command.RemoveBookingItemCommand;
import ej.domain.command.UpdateBookingItemCommand;
import ej.domain.event.booking.BasketConfirmedEvent;
import ej.domain.event.booking.BookingCanceledEvent;
import ej.domain.event.booking.BookingConfirmedEvent;
import ej.domain.event.booking.BookingCreatedEvent;
import ej.domain.event.booking.BookingItemAddedEvent;
import ej.domain.event.booking.BookingItemRemovedEvent;
import ej.domain.event.booking.BookingItemUpdatedEvent;
import ej.domain.event.booking.BookingRejectedEvent;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class Booking extends ReflectiveMutableCommandProcessingAggregate<Booking, BookingCommand> {

    private LocalDateTime createdTime;
    private BookingState state;
    private String customerAggregateId;
    private List<BookingItem> bookingItems = new ArrayList<>();

    public List<Event> process(AddBookingItemCommand cmd) {
        List<Event> events = new ArrayList<>(isNeedCreateBooking(cmd));
        events.add(new BookingItemAddedEvent(UUID.randomUUID().toString(), cmd.getPassenger(), cmd.getTrip()));
        return events;
    }

    public void apply(BookingCreatedEvent event) {
        this.createdTime = LocalDateTime.now();
        this.state = BookingState.PENDING;
        this.customerAggregateId = event.getCustomerAggregateId();
    }

    public void apply(BookingItemAddedEvent event) {
        addBookingItem(event.getBookingItemId(), event.getPassenger(), event.getTrip());
    }

    public List<Event> isNeedCreateBooking(AddBookingItemCommand cmd) {
        List<Event> events = new ArrayList<>();

        if (this.bookingItems.isEmpty()) {
            BookingCreatedEvent event = new BookingCreatedEvent();
            event.setCustomerAggregateId(cmd.getCustomerAggregateId());
            events.add(event);
        }
        return events;
    }

    public List<Event> process(RemoveBookingItemCommand cmd) {
        return EventUtil.events(new BookingItemRemovedEvent(cmd.getBookingItemId()));
    }

    public void apply(BookingItemRemovedEvent event) {
        removeBookingItem(event.getBookingItemId());
    }

    public List<Event> process(UpdateBookingItemCommand cmd) {
        return EventUtil.events(new BookingItemUpdatedEvent(
                cmd.getBookingItemId(),
                cmd.getPassenger(),
                cmd.getTrip())
        );
    }

    public void apply(BookingItemUpdatedEvent event) {
        updateBookingItem(event.getBookingItemId(), new BookingItem(event.getBookingItemId(), event.getPassenger(), event.getTrip()));
    }

    public List<Event> process(ConfirmBasketCommand cmd) {
        return EventUtil.events(new BasketConfirmedEvent(cmd.getCustomerAggregateId(), cmd.getBookingAggregateId()));
    }

    public void apply(BasketConfirmedEvent event) {
        noteBasketConfirmed();
    }

    public List<Event> process(CancelBookingCommand cmd) {
        return EventUtil.events(new BookingCanceledEvent());
    }

    public void apply(BookingCanceledEvent event) {
        noteBookingCanceled();
    }

    public List<Event> process(ConfirmBookingCommand cmd) {
        return EventUtil.events(new BookingConfirmedEvent());
    }

    public void apply(BookingConfirmedEvent event) {
        noteCreditReserved();
    }

    public List<Event> process(RejectBookingCommand cmd) {
        return EventUtil.events(new BookingRejectedEvent());
    }

    public void apply(BookingRejectedEvent event) {
        noteCreditReservationFailed();
    }

    public void addBookingItem(String bookingItemId, Passenger passenger, Trip trip) {
        BookingItem bookingItem = new BookingItem(bookingItemId, passenger, trip);
        this.bookingItems.add(bookingItem);
    }

    public void updateBookingItem(String bookingItemId, BookingItem bookingItem) {
        findBookingItemById(bookingItemId).ifPresent(item -> item.updateBookingItem(bookingItem));
    }

    public void removeBookingItem(String bookingItemId) {
        bookingItems.removeIf(i -> i.getBookingItemId().equals(bookingItemId));
    }

    public Optional<BookingItem> findBookingItemById(String bookingItemId) {
        return bookingItems.stream()
                .filter(bookingItem -> bookingItem.getBookingItemId().equals(bookingItemId))
                .findFirst();
    }

    public void noteCreditReserved() {
        this.state = BookingState.APPROVED;
    }

    public void noteCreditReservationFailed() {
        this.state = BookingState.REJECTED;
    }

    public void noteCreditLimitExceeded() {
        this.state = BookingState.CREDIT_LIMIT_EXCEEDED;
    }

    public void noteWaittingForPayment() {
        this.state = BookingState.WAITTING_FOR_PAYMENT;
    }

    public void noteBasketConfirmed() {
        this.state = BookingState.BASKET_CONFIRMED;
    }

    public void noteBookingCanceled() {
        this.state = BookingState.CANCELED;
    }

}
