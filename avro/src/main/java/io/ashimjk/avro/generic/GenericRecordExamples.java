package io.ashimjk.avro.generic;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

public class GenericRecordExamples {

    public static void main(String[] args) {
        // step 0: define schema
        final Schema.Parser parser = new Schema.Parser();
        final Schema schema = parser.parse("{\n" +
                "  \"type\": \"record\",\n" +
                "  \"namespace\": \"io.ashimjk\",\n" +
                "  \"name\": \"Customer\",\n" +
                "  \"doc\": \"Avro Schema for our Customer\",\n" +
                "  \"fields\": [\n" +
                "    {\"name\": \"first_name\", \"type\": \"string\", \"doc\": \"First name of a customer\"},\n" +
                "    {\"name\": \"last_name\", \"type\": \"string\", \"doc\": \"Last name of a customer\"},\n" +
                "    {\"name\": \"age\", \"type\": \"int\", \"doc\": \"Age at the time of registration\"},\n" +
                "    {\"name\": \"height\", \"type\": \"float\", \"doc\": \"Height at the time of registration in inches\"},\n" +
                "    {\"name\": \"weight\", \"type\": \"float\", \"doc\": \"Weight at the time of registration in kgs\"},\n" +
                "    {\"name\": \"automated_email\", \"type\": \"boolean\", \"default\": true, \"doc\": \"Field indicating if the notification emails should be sent\"}\n" +
                "  ]\n" +
                "}");

        // step 1: create a generic record
        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
        customerBuilder.set("first_name", "ashim");
        customerBuilder.set("last_name", "khadka");
        customerBuilder.set("age", 30);
        customerBuilder.set("height", 5.6f);
        customerBuilder.set("weight", 73f);
        customerBuilder.set("automated_email", false);

        GenericRecord customer = customerBuilder.build();
        System.out.println(customer);

        final File file = new File("customer-generic.avsc");

        // step 2: write that generic record to a file
        // write to a file
        final DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), file);
            dataFileWriter.append(customer);

            System.out.println("Written customer-generic.avsc");
        } catch (IOException e) {
            System.out.println("Couldn't write to a file");
            e.printStackTrace();
        }

        // step 3: read a generic record from a file
        // read from a file
        final DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader)) {
            // step 4: interpret as a generic record
            final GenericRecord customerRead = dataFileReader.next();

            System.out.println("Successfully read avro file");
            System.out.println(customerRead.toString());

            // get the data from generic record
            System.out.println("First Name : " + customerRead.get("first_name"));

            // read a non existent field
            System.out.println("Non existent field : " + customerRead.get("not_here"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
