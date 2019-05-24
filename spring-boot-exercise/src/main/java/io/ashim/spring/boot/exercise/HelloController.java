package io.ashim.spring.boot.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements ApplicationListener<AbstractAuthenticationFailureEvent> {

	@Value("${message}")
	String message;

	@Value("${randomValue}")
	String randomValue;

	@Value("${random.uuid}")
	String randomUUID;

	@Autowired
	MyConfiguration configuration;

	@GetMapping("/")
	public String getMessage() {
		return message;
	}

	@GetMapping("/randomValue")
	public String getRandomValue() {
		return randomValue;
	}

	@GetMapping("/randomUUID")
	public String getRandomUUID() {
		return randomUUID;
	}

	@GetMapping("/configuration")
	public String configuration() {
		return configuration.getMessage();
	}

	@Override
	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		System.out.println("Authentication Failure Event");
		System.out.println(event.getException().getMessage());
	}

}
