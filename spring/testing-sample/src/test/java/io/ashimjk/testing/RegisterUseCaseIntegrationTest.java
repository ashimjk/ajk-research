package io.ashimjk.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.testing.persistence.UserEntity;
import io.ashimjk.testing.persistence.UserRepository;
import io.ashimjk.testing.web.UserResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.liquibase.enabled=false",
        "spring.flyway.enabled=false"
})
@AutoConfigureMockMvc
public class RegisterUseCaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        UserEntity userEntity = userRepository.findByName("Zaphod");
        assertThat(userEntity.getEmail()).isEqualTo("zaphod@galaxy.net");
    }

}
