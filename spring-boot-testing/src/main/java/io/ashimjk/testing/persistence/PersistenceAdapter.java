package io.ashimjk.testing.persistence;

import io.ashimjk.testing.domain.SaveUserPort;
import io.ashimjk.testing.domain.User;
import org.springframework.stereotype.Component;

@Component
public class PersistenceAdapter implements SaveUserPort {

    private final UserRepository userRepository;

    public PersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long saveUser(User user) {
        UserEntity userEntity = new UserEntity(
            user.getName(),
            user.getEmail());
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return savedUserEntity.getId();
    }

    public User loadUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getRegistrationDate());
    }

}
