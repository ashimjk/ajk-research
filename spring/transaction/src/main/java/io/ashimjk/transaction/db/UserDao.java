package io.ashimjk.transaction.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author ashimjk on 2/9/2019
 */
@Service
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUser(User user) {
        insertUser(Collections.singletonList(user));
    }

    public void insertUser(List<User> users) {
        for (User user : users) {
            System.out.println("Inserting Data for User name : " + user.getName());

            this.jdbcTemplate.update(getInsertQuery(), ps -> {
                ps.setInt(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getAddress());
            });
        }

    }

    private String getInsertQuery() {
        return "insert into users (id, name, address) values(?, ?, ?)";
    }

    public void insertUser(List<User> users1, List<User> users2) {
        insertUser(users1);
        insertUser(users2);
    }

    public List<User> getUsers() {
        System.out.println("Retrieve all Users List...");
        return this.jdbcTemplate.query(getSelectQuery(), (rs, rowNum) -> User.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .address(rs.getString("address"))
                .build());
    }

    private String getSelectQuery() {
        return "select * from users";
    }

}
