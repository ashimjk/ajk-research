package io.ashimjk.spring.integration.transformer;

import io.ashimjk.spring.integration.Student;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransformerStudentService {

    @ServiceActivator(inputChannel = "integration.transformer.objectToJson.channel",
            outputChannel = "integration.transformer.jsonToObject.channel")
    public Message<?> receiveMessage(Message<?> message) {
        System.out.println("#############");
        System.out.println(message);
        System.out.println("#############");
        System.out.println("Object to json - " + message.getPayload());
        return message;
    }

    @ServiceActivator(inputChannel = "integration.transformer.jsonToObject.fromTransformer.channel")
    public void processJsonToObject(Message<?> message) {
        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
        System.out.println("#############");
        System.out.println("Json to Object - " + message.getPayload());
        Student payload = (Student) message.getPayload();
        Message<String> newMessage = MessageBuilder.withPayload(payload.toString()).build();
        replyChannel.send(newMessage);
    }

}
