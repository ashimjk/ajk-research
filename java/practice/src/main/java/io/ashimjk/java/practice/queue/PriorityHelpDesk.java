package io.ashimjk.java.practice.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static io.ashimjk.java.practice.queue.Category.*;
import static io.ashimjk.java.practice.queue.Customer.*;

public class PriorityHelpDesk {

    private static final Comparator<Enquiry> BY_CATEGORY = Comparator.comparing(Enquiry::getCategory);

    private final Queue<Enquiry> enquiries = new PriorityQueue<>(BY_CATEGORY);

    public void enquire(final Customer customer, final Category category) {
        this.enquiries.offer(new Enquiry(customer, category));
    }

    public void processAllEnquired() {
        Enquiry enquiry;

        while ((enquiry = this.enquiries.poll()) != null) {
            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        }
    }

    public static void main(String[] args) {
        PriorityHelpDesk priorityHelpDesk = new PriorityHelpDesk();
        priorityHelpDesk.enquire(JACK, PHONE);
        priorityHelpDesk.enquire(JILL, PRINTER);
        priorityHelpDesk.enquire(MARY, COMPUTER);

        priorityHelpDesk.processAllEnquired();
    }

}
