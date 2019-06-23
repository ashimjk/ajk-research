package io.ashimjk.converter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
class CurrencyRate {

    private String pair;

    @JsonDeserialize(as = BigDecimal.class)
    private Number rate;

    //  @JsonSerialize(converter = LocalDateTimeToStringConverter.class)
    //  @JsonDeserialize(converter = StringToLocalDateTimeConverter.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdated;

    static CurrencyRate of(String pair, Number rate, LocalDateTime lastUpdated) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.pair = pair;
        currencyRate.rate = rate;
        currencyRate.lastUpdated = lastUpdated;

        return currencyRate;
    }
}
