package io.ashimjk.spring.integration.service.database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "attendees")
@SequenceGenerator(name = "attendees_gen", sequenceName = "attendees_seq", allocationSize = 1)
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendees_gen")
    @Column(name = "attendee_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 80)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "title", length = 40)
    private String title;

    @Column(name = "company", length = 50)
    private String company;

    public Attendee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(id, attendee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
