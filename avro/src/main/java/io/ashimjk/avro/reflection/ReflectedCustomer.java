package io.ashimjk.avro.reflection;

import org.apache.avro.reflect.AvroDoc;
import org.apache.avro.reflect.Nullable;

@AvroDoc("Customer")
public class ReflectedCustomer {

    @AvroDoc("FirstName of the customer")
    private String firstName;

    @AvroDoc("Lastname of the customer")
    private String lastName;

    @Nullable
    @AvroDoc("Nickname of the customer")
    private String nickName;

    // needed by reflection
    public ReflectedCustomer() {
    }

    public ReflectedCustomer(String firstName, String lastName, String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "ReflectedCustomer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

}
