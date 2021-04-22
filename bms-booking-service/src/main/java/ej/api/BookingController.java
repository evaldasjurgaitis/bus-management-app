package ej.api;

import ej.api.request.AddBookingItemRequest;
import ej.api.request.UpdateBookingItemRequest;
import ej.api.response.BookingResponse;
import ej.domain.command.AddBookingItemCommand;
import ej.domain.command.CancelBookingCommand;
import ej.domain.command.ConfirmBookingCommand;
import ej.domain.command.RejectBookingCommand;
import ej.domain.command.RemoveBookingItemCommand;
import ej.domain.command.UpdateBookingItemCommand;
import ej.domain.exception.ResourceNotFoundException;
import ej.domain.projection.entity.BookingProjection;
import ej.domain.projection.service.BookingProjectionQueryService;
import ej.domain.service.BookingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/bookings", consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

    private BookingService bookingService;
    private BookingProjectionQueryService bookingProjectionQueryService;

    public BookingController(BookingService bookingService, BookingProjectionQueryService bookingProjectionQueryService) {
        this.bookingService = bookingService;
        this.bookingProjectionQueryService = bookingProjectionQueryService;
    }

    @GetMapping("/{bookingId}")
    public BookingResponse get(@PathVariable String bookingId) {
        return Optional.ofNullable(BookingResponse.fromProjection(bookingProjectionQueryService.findById(bookingId)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public CompletableFuture<BookingResponse> addBookingItem(@RequestBody AddBookingItemRequest request) {
        AddBookingItemCommand cmd = new AddBookingItemCommand(request);

        BookingProjection bookingProjection = getBookingProjection(cmd.getBookingItemId());

        return bookingService.createBooking(bookingProjection, cmd)
                .thenApply(e -> BookingResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    //TODO: Move to bookingItemController
    @PutMapping("/{bookingId}/items/{bookingItemId}")
    public CompletableFuture<BookingResponse> updateBookingItem(@PathVariable String bookingId, @PathVariable String bookingItemId, @RequestBody UpdateBookingItemRequest request) {
        UpdateBookingItemCommand cmd = new UpdateBookingItemCommand();
//        UpdateBookingItemCommand cmd = new UpdateBookingItemCommand(
//                bookingItemId,
//                request.getPassenger(),
//                request.getTrip()
//        );

        BookingProjection bookingProjection = getBookingProjection(bookingId);

        return bookingService.updateBooking(bookingProjection.getAggregateId(), cmd)
                .thenApply(e -> BookingResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    //TODO: Move to bookingItemController
    @DeleteMapping("/{bookingId}/items/{bookingItemId}")
    public CompletableFuture<BookingResponse> removeBookingItem(@PathVariable String bookingId, @PathVariable String bookingItemId) {
        RemoveBookingItemCommand cmd = new RemoveBookingItemCommand(bookingItemId);

        BookingProjection bookingProjection = getBookingProjection(bookingId);

        return bookingService.updateBooking(bookingProjection.getAggregateId(), cmd)
                .thenApply(e -> BookingResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    @PostMapping("/{bookingId}/cancel")
    public CompletableFuture<BookingResponse> cancelBooking(@PathVariable String bookingId) {
        CancelBookingCommand cmd = new CancelBookingCommand(bookingId);

        BookingProjection bookingProjection = getBookingProjection(bookingId);

        return bookingService.updateBooking(bookingProjection.getAggregateId(), cmd)
                .thenApply(e -> BookingResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    //TODO: Tempory for testing purpose
    @PostMapping("/{bookingId}/reject")
    public CompletableFuture<BookingResponse> rejectBooking(@PathVariable String bookingId) {
        RejectBookingCommand cmd = new RejectBookingCommand(bookingId);

        BookingProjection bookingProjection = getBookingProjection(bookingId);

        return bookingService.updateBooking(bookingProjection.getAggregateId(), cmd)
                .thenApply(e -> BookingResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    //TODO: Tempory for testing purpose
    @PostMapping("/{bookingId}/confirm")
    public CompletableFuture<BookingResponse> confirmBooking(@PathVariable String bookingId) {
        ConfirmBookingCommand cmd = new ConfirmBookingCommand(bookingId);

        BookingProjection bookingProjection = getBookingProjection(bookingId);

        return bookingService.updateBooking(bookingProjection.getAggregateId(), cmd)
                .thenApply(e -> BookingResponse.fromDomain(e.getAggregate(), e.getEntityId()));
    }

    //TODO: Find better solution
    private BookingProjection getBookingProjection(String bookingAggregateId) {
        if (bookingAggregateId == null || bookingAggregateId.isEmpty()) {
            return null;
        }

        return bookingProjectionQueryService.findById(bookingAggregateId);
    }

}
