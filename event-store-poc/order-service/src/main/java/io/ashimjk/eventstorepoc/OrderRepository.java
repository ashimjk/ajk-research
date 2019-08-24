package io.ashimjk.eventstorepoc;

import io.ashimjk.eventstorepoc.domain.ItemOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ItemOrder, String> {
}
