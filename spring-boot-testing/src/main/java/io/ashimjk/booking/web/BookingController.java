package io.ashimjk.booking.web;

import io.ashimjk.booking.business.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingResultResource> bookFlight(
            @RequestParam("customerId") Long customerId,
            @RequestParam("flightNumber") String flightNumber) {

        bookingService.bookFlight(customerId, flightNumber);

        BookingResultResource bookingResult = BookingResultResource.builder()
                .success(true)
                .build();
        return ResponseEntity.ok(bookingResult);
    }

}
