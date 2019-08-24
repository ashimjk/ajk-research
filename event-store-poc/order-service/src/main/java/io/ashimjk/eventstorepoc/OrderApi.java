package io.ashimjk.eventstorepoc;

import io.ashimjk.eventstorepoc.domain.DeliveryAddress;
import io.ashimjk.eventstorepoc.domain.ItemOrder;
import io.ashimjk.eventstorepoc.domain.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApi {

    private final OrderRepository repository;

    @PostMapping
    public ResponseEntity<ItemOrder> create(@RequestBody OrderPlaceCommand command) {

        ItemOrder itemOrder =
                ItemOrder.create(
                        Name.create(command.getFirstName(), command.getMiddleName(), command.getLastName()),
                        new DeliveryAddress(command.getLocation()));

        itemOrder.place();

        return ResponseEntity.ok(repository.save(itemOrder));
    }
}
