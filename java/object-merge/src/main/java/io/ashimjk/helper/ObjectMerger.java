package io.ashimjk.helper;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ObjectMerger {
    @Generated private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMerger.class);
    private static final String JSON_EXCEPTION_MESSAGE = "Error while reading user updates json";
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectMapper doNotWriteNullsMapper = new ObjectMapper();

    static {
        mapper
            .findAndRegisterModules()
            .registerModule(PayloadHelper.getMoneyModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setDefaultMergeable(Boolean.TRUE)
            .setSerializationInclusion(Include.ALWAYS)
            .configOverride(List.class)
            .setMergeable(Boolean.FALSE);
        doNotWriteNullsMapper
            .findAndRegisterModules()
            .registerModule(PayloadHelper.getMoneyModule())
            .setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"))
            .setDateFormat(new SimpleDateFormat("dd-MM-yyyy"))
            .setSerializationInclusion(Include.NON_EMPTY)
            .setSerializationInclusion(Include.NON_NULL)
            .setVisibility(PropertyAccessor.ALL, Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }

    public ObjectMerger() {}

    public static <T> T applyUpdates(T original, Object updates) {
        return applyUpdates(original, updates, true);
    }

    public static <T> T applyUpdates(T original, Object updates, boolean ignoreNulls) {
        try {
            return applyUpdates(
                original,
                ignoreNulls
                    ? doNotWriteNullsMapper.writeValueAsString(updates)
                    : mapper.writeValueAsString(updates));
        } catch (IOException var4) {
            LOGGER.warn("Error while reading user updates json", var4);
            throw new IllegalStateException("Error while reading user updates json", var4);
        }
    }

    public static <T> T applyUpdates(T original, String updates) {
        try {
            ObjectReader updater = mapper.readerForUpdating(original);
            return updater.readValue(updates);
        } catch (IOException var3) {
            LOGGER.warn("Error while reading user updates json", var3);
            throw new IllegalStateException("Error while reading user updates json", var3);
        }
    }
}
