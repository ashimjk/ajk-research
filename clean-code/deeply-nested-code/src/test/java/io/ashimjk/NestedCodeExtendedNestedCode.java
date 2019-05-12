package io.ashimjk;

import io.ashimjk.NestedCode.MappedField;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class NestedCodeExtendedNestedCode {

    @Test
    public void testMappedField() {
        List<String> personal = Arrays.asList("firstName", "lastName");
        List<String> addresses = Arrays.asList("city", "country");

        MappedField personalMapped = new MappedField(personal);
        MappedField addressMapped = new MappedField(addresses);

        NestedCode nestedCode = new NestedCode(Arrays.asList(personalMapped, addressMapped));

        assertThat(nestedCode.getMappedField("firstName"), is(personalMapped));
        assertThat(nestedCode.getMappedField("nothing"), nullValue());

    }

}