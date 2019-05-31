package io.ashimjk.services.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.services.account.messaging.OrderSender;
import io.ashimjk.services.account.model.Account;
import io.ashimjk.services.account.repository.AccountRepository;
import io.ashimjk.services.messaging.Order;
import io.ashimjk.services.messaging.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrderSender orderSender;
    private ObjectMapper mapper = new ObjectMapper();

    public void process(final Order order) throws JsonProcessingException {
        LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
        List<Account> accounts = accountRepository.findByCustomer(order.getCustomerId());
        Account account = accounts.get(0);
        LOGGER.info("Account found: {}", mapper.writeValueAsString(account));
        if (order.getPrice() <= account.getBalance()) {
            order.setStatus(OrderStatus.ACCEPTED);
            account.setBalance(account.getBalance() - order.getPrice());
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        orderSender.send(order);
        LOGGER.info("Order response sent: {}", mapper.writeValueAsString(order));
    }

}
