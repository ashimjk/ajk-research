package io.ashimjk.number_to_words;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amount implements Serializable {

    public static final String TRANSACTION_AMOUNT_VALUE_SHOULD_NOT_BE_NULL =
        "transaction amount value should not be null";
    private static final long serialVersionUID = 3103663860867776652L;
    @NotNull(message = TRANSACTION_AMOUNT_VALUE_SHOULD_NOT_BE_NULL)
    private Money value;

    private String words;

    public static Amount of(String amountInString) {
        Amount amount = new Amount();
        amount.setValue(Money.parse(amountInString));
        return amount;
    }

    public static Amount of(Money money) {
        Amount amount = new Amount();
        amount.setValue(money);
        return amount;
    }

    public static Amount deduct(Amount amount, Amount amount1) {
        return Amount.of(amount.getValue().minus(amount1.getValue()));
    }

    public static Amount add(Amount amount, Amount amount1) {
        return Amount.of(amount.getValue().plus(amount1.getValue()));
    }

    public void setValue(Money value) {
        this.value = value;
        this.words = NumberToWordsEnricher.enrich(value.abs());
    }

    public void add(Amount amount) {
        this.value = this.value.plus(amount.value);
    }

    public String getWords() {
        return NumberToWordsEnricher.enrich(value.abs());
    }
}
