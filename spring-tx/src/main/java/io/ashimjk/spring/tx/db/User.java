package io.ashimjk.spring.tx.db;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ashimjk on 2/9/2019
 */
@Getter
@Setter
@Builder
@ToString
public class User {

    private int id;
    private String name;
    private String address;

}
