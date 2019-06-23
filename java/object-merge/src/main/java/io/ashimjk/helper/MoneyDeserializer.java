package io.ashimjk.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.joda.money.Money;

import java.io.IOException;

public class MoneyDeserializer extends JsonDeserializer<Money> {
    public MoneyDeserializer() {}

    public Money deserialize(JsonParser jp, DeserializationContext deserializationContext)
        throws IOException {
        JsonNode moneyTree = jp.readValueAsTree();
        String moneyString = moneyTree.asText();
        return moneyString == null ? null : Money.parse(moneyString);
    }
}
