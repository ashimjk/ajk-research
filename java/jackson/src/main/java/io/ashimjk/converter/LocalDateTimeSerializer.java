package io.ashimjk.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    @Override
    public void serialize(
        LocalDateTime o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {

        String output = o.format(FORMATTER);
        jsonGenerator.writeString(output);
    }
}
