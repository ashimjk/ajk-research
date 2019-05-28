package io.ashim.spring.boot.exercise;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

	private final JdbcTemplate jdbcTemplate;

	public CustomerController(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

	@GetMapping("/customer")
	public List<Map<String, Object>> getCustomers() {
		return jdbcTemplate.queryForList("select * from customer");
	}

}
