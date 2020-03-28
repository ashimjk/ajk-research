package io.ashimjk.spring.integration.transformer;

import io.ashimjk.spring.integration.Student;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TransformerGateway {

    @Gateway(requestChannel = "integration.gateway.channel")
    String sendMessage(String name);

    @Gateway(requestChannel = "integration.transformer.gateway.channel")
    String processStudentDetails(Student student);

}
