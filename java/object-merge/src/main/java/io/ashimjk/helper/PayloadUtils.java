package io.ashimjk.helper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PayloadUtils {
    public PayloadUtils() {}

    public static <T> T deepClone(T object) {
        if (object == null) {
            return null;
        } else {
            try {
                ObjectMapper objectMapper =
                    (new ObjectMapper())
                        .findAndRegisterModules()
                        .registerModule(PayloadHelper.getMoneyModule())
                        .setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"))
                        .setDateFormat(new SimpleDateFormat("dd-MM-yyyy"))
                        .setSerializationInclusion(Include.ALWAYS);
                return (T)
                    objectMapper.readValue(objectMapper.writeValueAsString(object), object.getClass());
            } catch (IOException var2) {
                throw new ConversionException("Error while cloning object", var2);
            }
        }
    }
}
