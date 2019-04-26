package io.ashimjk.prime.generator.original;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by sdargo on 11/01/17.
 */
public class PrimePrinterTest {

	private PrintStream out;
	private String expectedOutputFileName;
	private String actualOutputFileName;

	@Before
	public void setup() throws Exception {
		out = System.out;
		expectedOutputFileName = "src/test/resources/gold.txt";
		actualOutputFileName = "src/test/resources/lead.txt";

		System.setOut(new PrintStream(new FileOutputStream(actualOutputFileName)));
	}

	@After
	public void teardown() {
		System.setOut(out);
		new File(actualOutputFileName).delete();
	}

	@Test
	public void makeSureOutputMatchesGold() throws Exception {
		PrimePrinter.main(new String[0]);
		BufferedReader gold = new BufferedReader(new FileReader(expectedOutputFileName));
		BufferedReader lead = new BufferedReader(new FileReader(actualOutputFileName));
		String line;
		while ((line = gold.readLine()) != null) {
			assertEquals(line, lead.readLine());
		}
		assertEquals(null, lead.readLine());
	}

}
