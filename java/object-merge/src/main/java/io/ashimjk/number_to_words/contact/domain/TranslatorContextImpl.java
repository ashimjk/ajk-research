package io.ashimjk.number_to_words.contact.domain;

import io.ashimjk.number_to_words.contact.Language;
import io.ashimjk.number_to_words.contact.Translator;
import io.ashimjk.number_to_words.contact.TranslatorContext;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class TranslatorContextImpl implements TranslatorContext {
    private Map<Language, Translator> translators = new EnumMap(Language.class);

    public TranslatorContextImpl() {}

    public String translateMinimal(BigDecimal amount, Language language, String currencyCode) {
        this.validateInputs(amount, language, currencyCode);
        Translator translator = this.translators.get(language);
        String result =
            this.bindIntegersTranslation(amount, Currency.get(currencyCode, language), translator);
        String numAsString = amount.toString();
        if (numAsString.contains(".")) {
            result = result + (result.length() > 0 ? translator.getDelimiter() : "");
            result =
                result
                    + this.extractFractions(numAsString)
                    + this.bindFractionsTranslation(
                    translator, "0.0", Currency.get(currencyCode, language));
        }

        return result;
    }

    private void validateInputs(BigDecimal amount, Language language, String currencyCode) {
        Objects.requireNonNull(amount, "amount can't be null");
        Objects.requireNonNull(language, "language can't be null");
        Objects.requireNonNull(currencyCode, "currencyCode can't be null");
        this.requireNonNegative(amount, "amount can't be null");
    }

    public String translate(BigDecimal amount, Language language, String currencyCode) {
        this.validateInputs(amount, language, currencyCode);
        Translator translator = this.translators.get(language);
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            return translator.translateZero(Currency.get(currencyCode, language));
        } else {
            String result =
                this.bindIntegersTranslation(amount, Currency.get(currencyCode, language), translator);
            if (amount.stripTrailingZeros().scale() > 0) {
                result = result + (result.length() > 0 ? translator.getDelimiter() : "");
                result =
                    result
                        + this.bindFractionsTranslation(
                        translator,
                        this.getFormattedAmount(amount, currencyCode),
                        Currency.get(currencyCode, language));
            }

            return result;
        }
    }

    private String getFormattedAmount(BigDecimal amount, String currencyCode) {
        return amount
            .setScale(java.util.Currency.getInstance(currencyCode).getDefaultFractionDigits(), 4)
            .toString();
    }

    private String bindIntegersTranslation(
        BigDecimal amount, Map<String, String> currency, Translator translator) {
        long number = amount.longValue();
        return number == 0L ? "" : translator.translate(number, currency, OperationType.INTEGER);
    }

    private String bindFractionsTranslation(
        Translator translator, String numAsString, Map<String, String> currency) {
        return translator.translate(
            this.extractFractions(numAsString), currency, OperationType.FRACTION);
    }

    private Long extractFractions(String numAsString) {
        return Long.valueOf(numAsString.substring(numAsString.lastIndexOf(46) + 1));
    }

    private void requireNonNegative(BigDecimal amount, String message) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new UnsupportedOperationException(message);
        }
    }

    public void addTranslator(Language language, Translator translator) {
        this.translators.putIfAbsent(language, translator);
    }
}
