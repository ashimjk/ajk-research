package pl.piomin.services.account;

import brave.sampler.RateLimitingSampler;
import brave.sampler.Sampler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import pl.piomin.services.account.model.Account;
import pl.piomin.services.account.repository.AccountRepository;
import pl.piomin.services.account.service.AccountService;
import pl.piomin.services.messaging.Order;

@SpringBootApplication
@EnableBinding(Processor.class)
public class AccountApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApplication.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private AccountService service;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    public void receiveOrder(Order order) throws JsonProcessingException {
        LOGGER.info("Order received: {}", mapper.writeValueAsString(order));
        service.process(order);
    }

    @Bean
    AccountRepository repository() {
        AccountRepository repository = new AccountRepository();
        repository.add(new Account("1234567890", 50000, 1L));
        repository.add(new Account("1234567891", 50000, 1L));
        repository.add(new Account("1234567892", 0, 1L));
        repository.add(new Account("1234567893", 50000, 2L));
        repository.add(new Account("1234567894", 0, 2L));
        repository.add(new Account("1234567895", 50000, 2L));
        repository.add(new Account("1234567896", 0, 3L));
        repository.add(new Account("1234567897", 50000, 3L));
        repository.add(new Account("1234567898", 50000, 3L));
        return repository;
    }

    @Bean
    public Sampler defaultSampler() {
        return RateLimitingSampler.create(3);
    }

}
