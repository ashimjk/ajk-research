package io.ashimjk.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class ExampleMain {

    @SneakyThrows
    public static void main(String[] args) {
        CurrencyRate cr =
            CurrencyRate.of(
                "USD/JPY",
                new BigDecimal(109.15).setScale(2, RoundingMode.CEILING),
                LocalDateTime.now());

        ObjectMapper om = new ObjectMapper();
        String s2 = om.writeValueAsString(cr);
        System.out.println("JSON string: " + s2);

        System.out.println("-- JSON to Java object --");
        CurrencyRate cr2 = om.readValue(s2, CurrencyRate.class);
        System.out.println("Java Object: " + cr2);
    }
}
