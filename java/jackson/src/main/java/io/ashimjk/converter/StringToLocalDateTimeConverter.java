package io.ashimjk.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

import static io.ashimjk.converter.LocalDateTimeToStringConverter.FORMATTER;

public class StringToLocalDateTimeConverter extends StdConverter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        return LocalDateTime.parse(s, FORMATTER);
    }
}
