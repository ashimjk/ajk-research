package io.ashimjk.booking.business;

import io.ashimjk.booking.data.Booking;
import io.ashimjk.booking.data.BookingRepository;
import io.ashimjk.customer.data.Customer;
import io.ashimjk.customer.data.CustomerRepository;
import io.ashimjk.flight.data.Flight;
import io.ashimjk.flight.data.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookingServiceTest {

    private static final String FLIGHT_NUMBER = "Oceanic 815";

    private CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

    private FlightService flightService = Mockito.mock(FlightService.class);

    private BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);

    private BookingService bookingService;

    @BeforeEach
    void setup() {
        this.bookingService = new BookingService(bookingRepository, customerRepository, flightService);
    }

    @Test
    void bookFlightReturnsBooking() {
        when(customerRepository.findById(42L)).thenReturn(customer());
        when(flightService.findFlight(FLIGHT_NUMBER)).thenReturn(flight());
        when(bookingRepository.save(eq(booking()))).thenReturn(booking());
        Booking booking = bookingService.bookFlight(42L, FLIGHT_NUMBER);

        assertThat(booking).isNotNull();
        verify(bookingRepository).save(eq(booking));

    }

    private Optional<Flight> flight() {
        return Optional.of(Flight.builder()
                .flightNumber(FLIGHT_NUMBER)
                .airline("Oceanic")
                .build());
    }

    private Booking booking() {
        return Booking.builder()
                .flightNumber(FLIGHT_NUMBER)
                .customer(customer().orElse(new Customer()))
                .build();
    }

    private Optional<Customer> customer() {
        return Optional.of(Customer.builder()
                .id(42L)
                .name("Hurley")
                .build());
    }

}