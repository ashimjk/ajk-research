package io.ashimjk;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class OriginalNestedCodeTest {

    @Test
    public void testMappedField() {
        List<String> personal = Arrays.asList("firstName", "lastName");
        List<String> addresses = Arrays.asList("city", "country");

        OriginalNestedCode.MappedField personalMapped = new OriginalNestedCode.MappedField(personal);
        OriginalNestedCode.MappedField addressMapped = new OriginalNestedCode.MappedField(addresses);

        OriginalNestedCode nestedCode = new OriginalNestedCode(Arrays.asList(personalMapped, addressMapped));

        assertThat(nestedCode.getMappedField("firstName"), is(personalMapped));
        assertThat(nestedCode.getMappedField("nothing"), nullValue());

    }

}