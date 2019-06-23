package io.ashimjk.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.money.format.MoneyAmountStyle;
import org.joda.money.format.MoneyFormatterBuilder;

import java.io.IOException;

public class MoneySerializer extends JsonSerializer<Money> {
    public MoneySerializer() {}

    public void serialize(Money value, JsonGenerator jgen, SerializerProvider serializerProvider)
        throws IOException {
        if (value == null) {
            jgen.writeNull();
        } else {
            String formattedAmount =
                (new MoneyFormatterBuilder())
                    .appendCurrencyCode()
                    .appendLiteral(" ")
                    .appendAmount(MoneyAmountStyle.ASCII_DECIMAL_POINT_NO_GROUPING)
                    .toFormatter()
                    .print(
                        Money.of(CurrencyUnit.of(value.getCurrencyUnit().getCode()), value.getAmount()));
            jgen.writeString(formattedAmount);
        }
    }
}
