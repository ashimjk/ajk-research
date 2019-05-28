package io.ashimjk.testing.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=validate",
    "spring.liquibase.enabled=false",
    "spring.flyway.enabled=true"
})
class FlywayTest {

    @Test
    void databaseHasBeenInitialized() {

    }

}