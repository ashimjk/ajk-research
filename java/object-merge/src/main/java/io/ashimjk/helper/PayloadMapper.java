package io.ashimjk.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.money.Money;

import java.io.IOException;
import java.util.Map;

public class PayloadMapper {
    private ObjectMapper objectMapper;

    public PayloadMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T convertToObject(Object object, Class<T> clazz) {
        return this.objectMapper.convertValue(object, clazz);
    }

    public String convertToString(Object object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            throw new ConversionException(var3);
        }
    }

    public Map<String, Object> convertToMap(Object object) {
        return (Map) this.objectMapper.convertValue(object, Map.class);
    }

    public <T> T toObject(String objectInString, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(objectInString, clazz);
        } catch (IOException var4) {
            throw new ConversionException(var4);
        }
    }

    public <T> T toObject(String response, TypeReference typeReference) {
        try {
            return this.objectMapper.readValue(response, typeReference);
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    private SimpleModule getMoneyModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Money.class, new MoneyDeserializer());
        module.addSerializer(Money.class, new MoneySerializer());
        return module;
    }
}
