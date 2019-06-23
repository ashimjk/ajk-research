package io.ashimjk.number_to_words;

import io.ashimjk.number_to_words.contact.Language;
import io.ashimjk.number_to_words.contact.TranslatorContext;
import io.ashimjk.number_to_words.contact.domain.EnglishTranslator;
import io.ashimjk.number_to_words.contact.domain.TranslatorContextImpl;
import org.joda.money.Money;

public class NumberToWordsEnricher {

    private static final TranslatorContext context;

    static {
        context = new TranslatorContextImpl();
        context.addTranslator(Language.ENGLISH, new EnglishTranslator());
    }

    public static String enrich(Money money) {
        if (money == null) {
            return null;
        }
        return context.translate(
            money.getAmount(), Language.ENGLISH, money.getCurrencyUnit().getCode());
    }
}
