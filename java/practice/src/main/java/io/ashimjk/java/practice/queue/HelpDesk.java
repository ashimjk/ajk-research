package io.ashimjk.java.practice.queue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Predicate;

import static io.ashimjk.java.practice.queue.Category.PRINTER;

public class HelpDesk {

    private final Queue<Enquiry> enquiries = new ArrayDeque<>();

    public void enquire(final Customer customer, final Category category) {
        this.enquiries.offer(new Enquiry(customer, category));
    }

    public void processPrinterEnquiry() {
        this.processEnquiry(
                enq -> enq.getCategory() == PRINTER,
                "Is it out of paper?");
    }

    public void processGeneralEnquiry() {
        this.processEnquiry(
                enq -> enq.getCategory() != PRINTER,
                "Have you tried turning it off and on again?");
    }

    private void processEnquiry(Predicate<Enquiry> predicate, String message) {
        final Enquiry enquiry = this.enquiries.peek();
        if (enquiry != null && predicate.test(enquiry)) {
            this.enquiries.poll();
            enquiry.getCustomer().reply(message);
        } else {
            System.out.println("No work to do, let's have some coffee!");
        }
    }

    public void processAllEnquired() {
        Enquiry enquiry;

        while ((enquiry = this.enquiries.poll()) != null) {
            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        }

        // while (!this.enquiries.isEmpty()) {
        //     final Enquiry enquiry = this.enquiries.remove();
        //     enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        // }
    }

    public static void main(String[] args) {
        HelpDesk helpDesk = new HelpDesk();
        helpDesk.enquire(Customer.JACK, Category.PHONE);
        helpDesk.enquire(Customer.JILL, PRINTER);

        // helpDesk.processAllEnquired();
        helpDesk.processPrinterEnquiry();
        helpDesk.processGeneralEnquiry();
        helpDesk.processPrinterEnquiry();
    }

}
