package ej.domain.projection.process;

import ej.config.IdempotentEventHandler;
import ej.config.TransactionExecutor;
import ej.domain.event.booking.BookingCanceledEvent;
import ej.domain.event.booking.BookingConfirmedEvent;
import ej.domain.event.booking.BookingCreatedEvent;
import ej.domain.event.booking.BookingItemAddedEvent;
import ej.domain.event.booking.BookingItemRemovedEvent;
import ej.domain.event.booking.BookingRejectedEvent;
import ej.domain.projection.embeddable.BookingState;
import ej.domain.projection.embeddable.Passenger;
import ej.domain.projection.embeddable.Trip;
import ej.domain.projection.entity.BookingItemProjection;
import ej.domain.projection.entity.BookingProjection;
import ej.domain.projection.service.BookingProjectionQueryService;
import ej.domain.projection.service.BookingProjectionService;
import ej.idempotent.ProcessedEventService;
import ej.idempotent.entity.ProcessedBookingProjectionEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import java.time.ZonedDateTime;

@EventSubscriber(id = "bookingProjectionEventHandlers")
public class BookingProjectionProcessManager extends IdempotentEventHandler<ProcessedBookingProjectionEvent> {

    private final BookingProjectionService bookingProjectionService;
    private final BookingProjectionQueryService bookingProjectionQueryService;

    public BookingProjectionProcessManager(ProcessedEventService processedEventService,
                                           TransactionExecutor transactionExecutor,
                                           BookingProjectionService bookingProjectionService,
                                           BookingProjectionQueryService bookingProjectionQueryService) {
        super(processedEventService, ProcessedBookingProjectionEvent.class, transactionExecutor);
        this.bookingProjectionService = bookingProjectionService;
        this.bookingProjectionQueryService = bookingProjectionQueryService;
    }

    @EventHandlerMethod
    public void createdBooking(DispatchedEvent<BookingCreatedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingCreatedEvent event = (BookingCreatedEvent) e;

                BookingProjection bookingProjection = new BookingProjection();
                bookingProjection.setAggregateId(ee.getEntityId());
                bookingProjection.setBookingState(BookingState.PENDING);
                bookingProjection.setCreatedTime(ZonedDateTime.now());
                bookingProjection.setCustomerAggregateId(event.getCustomerAggregateId());

                bookingProjectionService.save(bookingProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void addedBookingItem(DispatchedEvent<BookingItemAddedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingProjection bookingProjection = bookingProjectionQueryService.findById(ee.getEntityId());
                BookingItemAddedEvent event = (BookingItemAddedEvent) e;
                BookingItemProjection bookingItemProjection = new BookingItemProjection();
                bookingItemProjection.setBookingProjection(bookingProjection);
                mapToProjection(bookingItemProjection, event);
                bookingProjection.getBookingItems().add(bookingItemProjection);
                bookingProjectionService.save(bookingProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void removedBookingItem(DispatchedEvent<BookingItemRemovedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingProjection bookingProjection = bookingProjectionQueryService.findById(ee.getEntityId());
                BookingItemRemovedEvent event = (BookingItemRemovedEvent) e;
                bookingProjection.removeItem(event.getBookingItemId());
                bookingProjectionService.save(bookingProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void canceledBooking(DispatchedEvent<BookingCanceledEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingProjection bookingProjection = bookingProjectionQueryService.findById(ee.getEntityId());
                bookingProjectionService.save(bookingProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void rejectedBooking(DispatchedEvent<BookingRejectedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingProjection bookingProjection = bookingProjectionQueryService.findById(ee.getEntityId());
                bookingProjectionService.save(bookingProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @EventHandlerMethod
    public void confirmedBooking(DispatchedEvent<BookingConfirmedEvent> ee) {
        handleEvent(ee, e -> {
            try {
                BookingProjection bookingProjection = bookingProjectionQueryService.findById(ee.getEntityId());
                bookingProjectionService.save(bookingProjection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void mapToProjection(BookingItemProjection bookingItemProjection, BookingItemAddedEvent event) {
        bookingItemProjection.setBookingItemId(event.getBookingItemId());
        bookingItemProjection.setTrip(mapToTrip(event.getTrip()));
        bookingItemProjection.setPassenger(mapToPassenger(event.getPassenger()));
    }

    private Trip mapToTrip(ej.domain.aggregate.Trip trip) {
        return new Trip(
                trip.getId(),
                trip.getArrivalTime().toLocalDateTime(),
                trip.getDepartureTime().toLocalDateTime(),
                trip.getTravelTime(),
                trip.getDistance(),
                trip.getArrivalFrom(),
                trip.getDepartureTo());
    }

    private Passenger mapToPassenger(ej.domain.aggregate.Passenger passenger) {
        return new Passenger(
                passenger.getName(),
                passenger.getSurname(),
                passenger.getPhone());
    }

}
