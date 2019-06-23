package io.ashimjk.helper;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.joda.money.Money;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class PayloadHelper {
    private static ObjectMapper objectMapper;

    static {
        objectMapper =
            (new ObjectMapper())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"))
                .setDateFormat(new SimpleDateFormat("dd-MM-yyyy"))
                .setVisibility(PropertyAccessor.ALL, Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                .registerModule(new ParameterNamesModule(Mode.PROPERTIES))
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setDefaultMergeable(Boolean.TRUE)
                .setSerializationInclusion(Include.ALWAYS);
        objectMapper.registerModule(getMoneyModule());
    }

    private PayloadHelper() {}

    static SimpleModule getMoneyModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Money.class, new MoneyDeserializer());
        module.addSerializer(Money.class, new MoneySerializer());
        return module;
    }

    public static <T> T convertToObject(Object object, Class<T> clazz) {
        return objectMapper.convertValue(object, clazz);
    }

    public static String convertToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            throw new ConversionException(var2);
        }
    }

    public static Map<String, Object> convertToMap(Object object) {
        return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
    }

    public static <T> T toObject(String objectInString, Class<T> clazz) {
        try {
            return objectMapper.readValue(objectInString, clazz);
        } catch (IOException var3) {
            throw new ConversionException(var3);
        }
    }

    public static <T> T toObject(String response, TypeReference typeReference) {
        try {
            return objectMapper.readValue(response, typeReference);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
