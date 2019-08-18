package io.ashimjk.eventstorepoc;

import io.ashimjk.eventstorepoc.domain.ItemOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = OrderServiceApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ItemOrderApiTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void placeOrder() {
        OrderPlaceCommand command = new OrderPlaceCommand();
        command.setFirstName("Shekhar");
        command.setLastName("Rai");
        command.setLocation("Bhaisipati");

        ResponseEntity<ItemOrder> response = rest.postForEntity("/orders", command, ItemOrder.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
