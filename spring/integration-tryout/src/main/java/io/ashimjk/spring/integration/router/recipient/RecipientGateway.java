package io.ashimjk.spring.integration.router.recipient;

import io.ashimjk.spring.integration.Student;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface RecipientGateway {

    @Gateway(requestChannel = "recipient.router.channel")
    void process(Student student);

}
