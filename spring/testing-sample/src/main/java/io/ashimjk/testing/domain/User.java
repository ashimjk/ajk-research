package io.ashimjk.testing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime registrationDate;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
