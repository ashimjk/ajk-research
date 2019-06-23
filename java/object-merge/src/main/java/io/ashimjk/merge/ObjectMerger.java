package io.ashimjk.merge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ObjectMerger {
    public static final String JSON_EXCEPTION_MESSAGE = "Error while reading user updates json";

    public static <T> T applyUpdates(ObjectMapper mapper, T original, Object updates) {
        try {
            return applyUpdates(mapper, original, mapper.writeValueAsString(updates));
        } catch (IOException e) {
            log.warn(JSON_EXCEPTION_MESSAGE, e);
            throw new IllegalStateException(JSON_EXCEPTION_MESSAGE, e);
        }
    }

    public static <T> T applyUpdates(ObjectMapper mapper, T original, String updates) {
        try {
            ObjectReader updater = mapper.readerForUpdating(original);
            return updater.readValue(updates);
        } catch (IOException e) {
            log.warn(JSON_EXCEPTION_MESSAGE, e);
            throw new IllegalStateException(JSON_EXCEPTION_MESSAGE, e);
        }
    }
}
