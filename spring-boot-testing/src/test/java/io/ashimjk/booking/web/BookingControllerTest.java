package io.ashimjk.booking.web;

import io.ashimjk.booking.business.BookingService;
import io.ashimjk.booking.data.Booking;
import io.ashimjk.customer.data.Customer;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log
@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private BookingService bookingService;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(log::info);
    }

    @Test
    void bookFlightReturnsHttpStatusOk() throws Exception {

        String flightNumber = "Oceanic 815";

        when(bookingService.bookFlight(eq(42L), eq(flightNumber)))
                .thenReturn(expectedBooking());

        mockMvc.perform(
                post("/booking")
                        .param("customerId", "42")
                        .param("flightNumber", flightNumber))
                .andExpect(status().isOk());
    }

    private Booking expectedBooking() {
        return Booking.builder()
                .customer(Customer.builder()
                        .id(42L)
                        .name("Zaphod")
                        .build())
                .flightNumber("Oceanic 815")
                .build();
    }

}