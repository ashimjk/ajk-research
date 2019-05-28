package io.ashimjk.testing.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private LocalDateTime registrationDate;

    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
