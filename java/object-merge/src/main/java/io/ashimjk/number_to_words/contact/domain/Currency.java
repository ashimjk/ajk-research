package io.ashimjk.number_to_words.contact.domain;

import io.ashimjk.number_to_words.contact.Language;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class Currency {
    public static final String UNIT = "unit";
    public static final String SUB_UNIT = "subUnit";
    public static final String DISPLAY_NAME = "displayName";
    private static final Map<String, Map<Language, Map<String, String>>> currencies = new HashMap();

    private Currency() {}

    public static void put(String currencyCode, Language language, Map<String, String> value) {
        Map<Language, Map<String, String>> map = new EnumMap(Language.class);
        map.put(language, value);
        if (currencies.containsKey(currencyCode)) {
            currencies.get(currencyCode).putAll(map);
        } else {
            currencies.put(currencyCode, map);
        }
    }

    public static Map<String, String> get(String currencyCode, Language language) {
        return (Map) ((Map) currencies.get(currencyCode)).get(language);
    }
}
