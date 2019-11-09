package io.ashimjk.entitymerge;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

class EntityMerge {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityMerge.class);

    private static final String JSON_EXCEPTION_MESSAGE = "Error while reading user updates json";
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectMapper doNotWriteNullsMapper = new ObjectMapper();

    static {
        mapper.findAndRegisterModules()
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .setDefaultMergeable(Boolean.TRUE)
                .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                .configOverride(List.class)
                .setMergeable(Boolean.FALSE)
        ;

        doNotWriteNullsMapper.findAndRegisterModules()
                .setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"))
                .setDateFormat(new SimpleDateFormat("dd-MM-yyyy"))
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    static <T> T applyUpdates(T original, Object updates) {
        return applyUpdates(original, updates, true);
    }

    private static <T> T applyUpdates(T original, Object updates, boolean ignoreNulls) {
        try {
            return applyUpdates(original, ignoreNulls ? doNotWriteNullsMapper.writeValueAsString(updates) : mapper.writeValueAsString(updates));

        } catch (IOException var4) {
            LOGGER.warn(JSON_EXCEPTION_MESSAGE, var4);
            throw new IllegalStateException(JSON_EXCEPTION_MESSAGE, var4);
        }
    }

    @SneakyThrows
    static String json(Object object) {
        return mapper.writeValueAsString(object);
    }

    private static <T> T applyUpdates(T original, String updates) {
        try {
            ObjectReader updater = mapper.readerForUpdating(original);
            return updater.readValue(updates);

        } catch (IOException var3) {
            LOGGER.warn(JSON_EXCEPTION_MESSAGE, var3);
            throw new IllegalStateException(JSON_EXCEPTION_MESSAGE, var3);
        }
    }

}
