package io.ashimjk.transaction.sample;

import io.ashimjk.transaction.db.User;
import io.ashimjk.transaction.db.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author ashimjk on 2/9/2019
 */
@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    //	@Transactional
    public void insert() {
        User user1 = User.builder().id(1).name("ram").address("boudha").build();
        User user2 = User.builder().id(2).name("sita").address("hattisar").build();
        User user3 = User.builder().id(3).name("hari").address("kamalpokhari").build();
        User user4 = User.builder().id(4).name("shyam").address("dhapasi").build();

        // scenario 1
        scenario1(user1, user2, user3, user4);

        // scenario 2
        // scenario2(user1, user2, user3, user4);

        // scenario 3
        // scenario3(user1, user2, user3, user4);
    }

    private void scenario1(User user1, User user2, User user3, User user4) {
        this.userDao.insertUser(Arrays.asList(user1, user2));
        this.userDao.insertUser(Arrays.asList(user1, user2, user3, user4));
    }

    private void scenario2(User user1, User user2, User user3, User user4) {
        this.userDao.insertUser(Arrays.asList(user1, user2));
        this.userDao.insertUser(Arrays.asList(user3, user4));
    }

    private void scenario3(User user1, User user2, User user3, User user4) {
        this.userDao.insertUser(Arrays.asList(user1, user2), Arrays.asList(user3, user4));
    }

    public List<User> getUsers() {
        return this.userDao.getUsers();
    }

}
