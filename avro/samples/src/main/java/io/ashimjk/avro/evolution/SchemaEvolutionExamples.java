package io.ashimjk.avro.evolution;

import io.ashimjk.avro.model.CustomerV1;
import io.ashimjk.avro.model.CustomerV2;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SchemaEvolutionExamples {

    public static void main(String[] args) throws IOException {
        backwardCompatible();

        System.out.println();
        System.out.println();

        forwardCompatible();
    }

    private static void backwardCompatible() throws IOException {
        System.out.println("-------Test Backward Compatible-------\n");
        final CustomerV1 customerV1 = CustomerV1.newBuilder()
                .setFirstName("ashim")
                .setLastName("khadka")
                .setAge(30)
                .setHeight(5.6f)
                .setWeight(73)
                .setAutomatedEmail(false)
                .build();

        System.out.println("Customer V1 : " + customerV1);

        final File file = new File("customerV1-evolution.avro");

        // write to a file using customer v1 schema
        final DatumWriter<CustomerV1> datumWriter = new SpecificDatumWriter<>(CustomerV1.class);
        final DataFileWriter<CustomerV1> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(customerV1.getSchema(), file);
        dataFileWriter.append(customerV1);

        dataFileWriter.close();

        // read from a file using customer v2 schema
        final DatumReader<CustomerV2> datumReader = new SpecificDatumReader<>(CustomerV2.class);
        final DataFileReader<CustomerV2> dataFileReader = new DataFileReader<>(file, datumReader);
        final CustomerV2 customerV2 = dataFileReader.next();
        System.out.println("Customer V2 : " + customerV2);

        dataFileReader.close();
    }

    private static void forwardCompatible() throws IOException {
        System.out.println("-------Test Forward Compatible-------\n");
        final CustomerV2 customerV2 = CustomerV2.newBuilder()
                .setFirstName("ashim")
                .setLastName("khadka")
                .setAge(30)
                .setHeight(5.6f)
                .setWeight(73)
                .setPhoneNumber("9841542010")
                .setEmail("ashim.jung.khadka@gmail.com")
                .build();

        System.out.println("Customer V2 : " + customerV2);

        final File file = new File("customerV2-evolution.avro");

        // write to a file using customer v2 schema
        final DatumWriter<CustomerV2> datumWriter = new SpecificDatumWriter<>(CustomerV2.class);
        final DataFileWriter<CustomerV2> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(customerV2.getSchema(), file);
        dataFileWriter.append(customerV2);

        dataFileWriter.close();

        // read from a file using customer v1 schema
        final DatumReader<CustomerV1> datumReader = new SpecificDatumReader<>(CustomerV1.class);
        final DataFileReader<CustomerV1> dataFileReader = new DataFileReader<>(file, datumReader);
        final CustomerV1 customerV1 = dataFileReader.next();
        System.out.println("Customer V1 : " + customerV1);

        dataFileReader.close();
    }

}
