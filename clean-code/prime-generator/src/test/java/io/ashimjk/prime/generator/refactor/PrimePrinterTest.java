package io.ashimjk.prime.generator.refactor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PrimePrinterTest {

    private PrintStream out;
    private String actualOutputFileName;
    private String expectedOutputFileName;

    @Before
    public void setup() throws Exception {
        out = System.out;
        actualOutputFileName = "src/test/resources/lead.txt";
        expectedOutputFileName = "src/test/resources/gold.txt";

        System.setOut(new PrintStream(new FileOutputStream(actualOutputFileName)));
    }

    @After
    public void teardown() throws IOException {
        System.setOut(out);
        Files.delete(Paths.get(actualOutputFileName));
    }

    @Test
    public void makeSureOutputMatchesGold() throws Exception {
        PrimePrinter.main(new String[0]);
        BufferedReader lead = new BufferedReader(new FileReader(actualOutputFileName));
        BufferedReader gold = new BufferedReader(new FileReader(expectedOutputFileName));

        String line;

        while ((line = gold.readLine()) != null) {
            assertEquals(line, lead.readLine());
        }

        assertNull(lead.readLine());
    }

}
