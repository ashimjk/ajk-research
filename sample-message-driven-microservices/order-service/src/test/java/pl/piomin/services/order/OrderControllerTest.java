package pl.piomin.services.order;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.piomin.services.messaging.Order;
import pl.piomin.services.messaging.OrderStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OrderControllerTest {

    private TestRestTemplate template = new TestRestTemplate();

    @Test
    @Ignore
    public void testOrder() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            sendAndAcceptOrder();
            Thread.sleep(5000);
        }
    }

    private void sendAndAcceptOrder() {
        try {
            Order order = getOrder();

            order = template.postForObject("http://localhost:8090", order, Order.class);

            if (order.getStatus() != OrderStatus.REJECTED) {
                template.put("http://localhost:8090/{id}", null, order.getId());
            }

        } catch (Exception ignored) {
        }
    }

    private Order getOrder() {
        Order order = new Order();

        List<Long> productIds = Arrays.asList(
            getRandomLongValue(10),
            getRandomLongValue(10)
        );

        order.setCustomerId(getRandomLongValue(3));
        order.setProductIds(productIds);

        return order;
    }

    private long getRandomLongValue(int upperBound) {
        Random r = new Random();
        return (long) r.nextInt(upperBound) + 1;
    }

}
