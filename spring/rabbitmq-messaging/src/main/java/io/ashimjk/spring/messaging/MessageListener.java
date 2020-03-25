package io.ashimjk.spring.messaging;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MessageListener {

    //@RabbitListener(queues = QUEUE)
    //public void receiveMessage(PayloadMessage message) {
    //    log.info("Received message with default configuration and message is : {}", message.toString());
    //}

}
