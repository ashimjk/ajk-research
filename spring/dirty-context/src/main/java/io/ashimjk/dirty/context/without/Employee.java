package io.ashimjk.dirty.context.without;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
public class Employee {

    private String name;

}
