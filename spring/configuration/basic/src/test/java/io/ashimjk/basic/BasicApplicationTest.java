package io.ashimjk.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest
class BasicApplicationTest {

  @Value("${configuration.projectName}")
  private String projectName;

  @Test
  @DisplayName("Get project name from configuration")
  void testProjectName() {
    assertEquals("Spring Boot", projectName);
  }
}
