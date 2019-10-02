package io.ashimjk.kafkacloudstream;

import lombok.Value;

@Value
public class Person {

    private String name;
    private String address;

    public static Person create(String name, String address) {
        return new Person(name, address);
    }
}
