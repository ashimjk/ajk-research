package io.ashimjk.testing;

import io.ashimjk.testing.domain.RegisterUseCase;
import io.ashimjk.testing.domain.User;
import io.ashimjk.testing.persistence.UserEntity;
import io.ashimjk.testing.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class MockBeanTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private RegisterUseCase registerUseCase;

    @Test
    void testRegister() {
        // given
        User user = new User("Zaphod", "zaphod@galaxy.net");
        boolean sendWelcomeMail = true;

        // when
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity(1L));
        Long userId = registerUseCase.registerUser(user, sendWelcomeMail);

        // then
        assertThat(userId).isEqualTo(1L);
    }

    private UserEntity userEntity(Long id) {
        return new UserEntity(id, "Zaphod", "zaphod@galaxy.net", LocalDateTime.now());
    }

}
