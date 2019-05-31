package io.ashimjk.testing.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.liquibase.enabled=false",
        "spring.flyway.enabled=false"
})
class SqlTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("createUser.sql")
    void whenInitializedByDbUnit_thenFindsByName() {
        UserEntity user = userRepository.findByName("Zaphod Beeblebrox");
        assertThat(user).isNotNull();
    }

}