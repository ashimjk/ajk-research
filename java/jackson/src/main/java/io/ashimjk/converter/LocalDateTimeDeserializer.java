package io.ashimjk.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import static io.ashimjk.converter.LocalDateTimeSerializer.FORMATTER;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(
        JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        return LocalDateTime.parse(jsonParser.getText(), FORMATTER);
    }
}
