package io.ashimjk.number_to_words.contact;

import java.math.BigDecimal;

public interface TranslatorContext {
    String SPACE = " ";

    String translate(BigDecimal var1, Language var2, String var3);

    default String translateWithPostfix(
        BigDecimal amount, Language language, String currencyCode, String postfix) {
        return this.translate(amount, language, currencyCode) + " " + postfix;
    }

    default String translateMinimalWithPostfix(
        BigDecimal amount, Language language, String currencyCode, String postfix) {
        return this.translateMinimal(amount, language, currencyCode) + " " + postfix;
    }

    void addTranslator(Language var1, Translator var2);

    String translateMinimal(BigDecimal var1, Language var2, String var3);
}
