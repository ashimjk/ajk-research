package io.ashimjk.entityrelationship.support;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@UtilityClass
public class ObjectMerger {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMerger.class);
    private static final String JSON_EXCEPTION_MESSAGE = "Error while reading user updates json";
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectMapper doNotWriteNullsMapper = new ObjectMapper();

    static {
        mapper.findAndRegisterModules()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setDefaultMergeable(Boolean.TRUE)
                .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                .configOverride(List.class)
                .setMergeable(Boolean.FALSE);

        doNotWriteNullsMapper.findAndRegisterModules()
                .setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"))
                .setDateFormat(new SimpleDateFormat("dd-MM-yyyy"))
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static <T> T applyUpdates(T original, Object updates) {
        return applyUpdates(original, updates, true);
    }

    private static <T> T applyUpdates(T original, Object updates, boolean ignoreNulls) {
        try {
            return applyUpdates(original, ignoreNulls ? doNotWriteNullsMapper.writeValueAsString(updates) : mapper.writeValueAsString(updates));
        } catch (IOException ex) {
            LOGGER.warn(JSON_EXCEPTION_MESSAGE, ex);
            throw new IllegalStateException("Error while reading user updates json", ex);
        }
    }

    private static <T> T applyUpdates(T original, String updates) {
        try {
            ObjectReader updater = mapper.readerForUpdating(original);
            return updater.readValue(updates);
        } catch (IOException ex) {
            LOGGER.warn("Error while reading user updates json", ex);
            throw new IllegalStateException("Error while reading user updates json", ex);
        }
    }

}
