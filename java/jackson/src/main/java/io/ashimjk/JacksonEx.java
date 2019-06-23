package io.ashimjk;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.time.LocalDateTime;

public class JacksonEx {

    @SneakyThrows
    public static void main(String[] args) {

        simpleMapper();

        readInjectableType();

        writeInjectableType();
    }

    @SneakyThrows
    private static void readInjectableType() {
        String json = "{\"ref\":\"123\",\"amount\":\"123.00\"}";

        InjectableValues.Std iv = new InjectableValues.Std();
        iv.addValue("createdDate", LocalDateTime.now());

        ObjectMapper om = new ObjectMapper();
        om.setInjectableValues(iv);
        LC cr = om.readValue(json, LC.class);
        System.out.println(cr);
    }

    @SneakyThrows
    private static void writeInjectableType() {
        LC lc = getLc();

        InjectableValues.Std std = new InjectableValues.Std();
        std.addValue("createdDate", LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setInjectableValues(std);

        String result = mapper.writeValueAsString(lc);

        System.out.println(result);
    }

    @SneakyThrows
    private static void simpleMapper() {
        LC lc = getLc();

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writeValueAsString(lc);

        System.out.println(output);
    }

    private static LC getLc() {
        LC lc = new LC();
        lc.setRef("123");
        lc.setAmount("123.00");

        return lc;
    }

    @Getter
    @Setter
    @ToString
    static class LC {
        private String ref;
        private String amount;

        @JsonIgnore
        @JacksonInject("createdDate")
        private LocalDateTime createdDate;
    }
}
