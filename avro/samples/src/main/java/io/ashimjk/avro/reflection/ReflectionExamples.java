package io.ashimjk.avro.reflection;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.File;
import java.io.IOException;

public class ReflectionExamples {

    public static void main(String[] args) {
        // here we use reflection to determine the schema
        final Schema schema = ReflectData.get().getSchema(ReflectedCustomer.class);
        System.out.println("schema : " + schema.toString(true));

        // step 2: write that reflected record to a file
        final File file = new File("customer-reflected.avro");

        // step 2: write that reflected record to a file
        // write to a file
        final DatumWriter<ReflectedCustomer> datumWriter = new ReflectDatumWriter<>(ReflectedCustomer.class);
        try (DataFileWriter<ReflectedCustomer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(schema, file);
            dataFileWriter.append(new ReflectedCustomer("ashim", "khadka", "Java"));

            System.out.println("Written customer-reflected.avro");
        } catch (IOException e) {
            System.out.println("Couldn't write to a file");
            e.printStackTrace();
        }

        // step 3: read a reflected record from a file
        // read from a file
        final DatumReader<ReflectedCustomer> datumReader = new ReflectDatumReader<>(schema);
        try (DataFileReader<ReflectedCustomer> dataFileReader = new DataFileReader<>(file, datumReader)) {
            // step 4: interpret as a reflected record
            final ReflectedCustomer customerRead = dataFileReader.next();

            System.out.println("Successfully read avro file");
            System.out.println(customerRead.toString());

            // get the data from reflected record
            System.out.println("First Name : " + customerRead.getFirstName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
