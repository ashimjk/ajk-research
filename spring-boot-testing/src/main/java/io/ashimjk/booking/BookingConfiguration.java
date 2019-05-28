package io.ashimjk.booking;

import io.ashimjk.booking.business.BookingService;
import io.ashimjk.booking.data.BookingRepository;
import io.ashimjk.customer.CustomerConfiguration;
import io.ashimjk.customer.data.CustomerRepository;
import io.ashimjk.flight.FlightConfiguration;
import io.ashimjk.flight.data.FlightService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CustomerConfiguration.class, FlightConfiguration.class})
@ComponentScan
public class BookingConfiguration {

    @Bean
    public BookingService bookingService(
        BookingRepository bookingRepository,
        CustomerRepository customerRepository,
        FlightService flightService) {
        return new BookingService(bookingRepository, customerRepository, flightService);
    }

}
