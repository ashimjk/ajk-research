package io.ashimjk.avro.specific;

import io.ashimjk.avro.model.Customer;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SpecificRecordExamples {

    public static void main(String[] args) {
        // step 1: create a specific record
        final Customer customer = Customer.newBuilder()
                .setFirstName("ashim")
                .setLastName("khadka")
                .setAge(30)
                .setHeight(5.6f)
                .setWeight(73)
                .setAutomatedEmail(false)
                .build();
        System.out.println(customer);

        // step 2: write that specific record to a file
        final File file = new File("customer-specific.avro");

        // write to a file
        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);
        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), file);
            dataFileWriter.append(customer);

            System.out.println("Written customer-specific.avro");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Couldn't write to a file");
            e.printStackTrace();
        }

        // step 3: read a specific record from a file
        // read from a file
        final DatumReader<Customer> datumReader = new SpecificDatumReader<>(customer.getSchema());
        try (DataFileReader<Customer> dataFileReader = new DataFileReader<>(file, datumReader)) {
            // step 4: interpret as a specific record
            final Customer customerRead = dataFileReader.next();

            System.out.println("Successfully read avro file");
            System.out.println(customerRead.toString());

            // get the data from specific record
            System.out.println("First Name : " + customerRead.getFirstName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}