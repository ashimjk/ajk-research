package io.ashimjk.number_to_words.contact;

import io.ashimjk.number_to_words.contact.domain.OperationType;

import java.util.Map;

public interface Translator {
    String translate(Long var1, Map<String, String> var2, OperationType var3);

    String getDelimiter();

    String translateZero(Map<String, String> var1);
}
