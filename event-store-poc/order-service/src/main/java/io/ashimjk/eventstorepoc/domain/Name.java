package io.ashimjk.eventstorepoc.domain;

import lombok.*;
import org.springframework.util.Assert;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Getter
public class Name {

    public static final String FIRST_NAME_SHOULD_NOT_BE_EMPTY = "firstName.should.not.be.empty";
    public static final String LAST_NAME_SHOULD_NOT_BE_EMPTY = "lastName.should.not.be.empty";
    private String firstName;
    private String middleName;
    private String lastName;

    public static Name create(String firstName, String middleName, String lastName) {
        Assert.hasText(firstName, FIRST_NAME_SHOULD_NOT_BE_EMPTY);
        Assert.hasText(lastName, LAST_NAME_SHOULD_NOT_BE_EMPTY);
        return new Name(firstName, middleName, lastName);
    }
}
