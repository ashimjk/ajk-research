package io.ashimjk.java.practice.queue;

public class Enquiry {

    private final Customer customer;
    private final Category category;

    public Enquiry(Customer customer, Category category) {
        this.customer = customer;
        this.category = category;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Category getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return "Enquiry{" +
                "customer=" + this.customer +
                ", category=" + this.category +
                '}';
    }

}
