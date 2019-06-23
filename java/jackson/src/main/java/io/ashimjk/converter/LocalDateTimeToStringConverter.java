package io.ashimjk.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LocalDateTimeToStringConverter extends StdConverter<LocalDateTime, String> {

    static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    @Override
    public String convert(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }
}
