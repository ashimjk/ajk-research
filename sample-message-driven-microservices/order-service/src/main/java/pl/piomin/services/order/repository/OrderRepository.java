package pl.piomin.services.order.repository;

import org.springframework.stereotype.Repository;
import pl.piomin.services.messaging.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public Order add(Order order) {
        order.setId((long) (orders.size() + 1));
        orders.add(order);
        return order;
    }

    public void update(Order order) {
        orders.set(order.getId().intValue() - 1, order);
    }

    public Order findById(Long id) {
        Optional<Order> order = orders.stream().filter(p -> p.getId().equals(id)).findFirst();
        return order.orElse(null);
    }

    public List<Order> findAll() {
        return orders;
    }

    public void delete(Long id) {
        orders.remove(id.intValue());
    }

    public List<Order> find(List<Long> ids) {
        return orders.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
    }

    public int countByCustomerId(Long customerId) {
        return (int) orders.stream().filter(p -> p.getCustomerId().equals(customerId)).count();
    }
}
