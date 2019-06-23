package io.ashimjk.number_to_words.contact.domain;

import io.ashimjk.number_to_words.contact.Translator;

import java.util.*;
import java.util.function.BiFunction;

public class ArabicTranslator implements Translator {
    private static final String HUNDRED = " مئه";
    private static final String TWO_HUNDRED = " مئتان";
    private static final String DELIMITER = " و";
    private static final String ONE_SPACE = " ";
    private static final ThreadLocal<Deque<Long>> numbers;
    private static Map<Integer, String> dictionary = new HashMap<>();

    static {
        dictionary.put(0, " صفر");
        dictionary.put(1, " واحد");
        dictionary.put(2, " اثنين");
        dictionary.put(3, " ثلاثة");
        dictionary.put(4, " اربعة");
        dictionary.put(5, " خمسة");
        dictionary.put(6, " ستة");
        dictionary.put(7, " سبعة");
        dictionary.put(8, " ثمانية");
        dictionary.put(9, " تسعة");
        dictionary.put(10, " عشرة");
        dictionary.put(11, " احد عشر");
        dictionary.put(12, " اثنا عشر");
        dictionary.put(13, " ثلاثة عشر");
        dictionary.put(14, " اريعة عشر");
        dictionary.put(15, " خمسة عشر");
        dictionary.put(16, " ستة عشر");
        dictionary.put(17, " سبعة عشر");
        dictionary.put(18, " ثمانة عشر");
        dictionary.put(19, " تسعة عشر");
        dictionary.put(20, " عشرون");
        dictionary.put(30, " ثلاثون");
        dictionary.put(40, " اربعون");
        dictionary.put(50, " خمسون");
        dictionary.put(60, " ستون");
        dictionary.put(70, " سبعون");
        dictionary.put(80, " ثمانون");
        dictionary.put(90, " تسعون");
        numbers = ThreadLocal.withInitial(LinkedList::new);
    }

    private Map<OperationType, BiFunction<Long, Map<String, String>, String>>
        lessThanThreeConditions = new EnumMap<>(OperationType.class);
    private String[][] families =
        new String[][]{
            {"", ""},
            {" الف", " الاف"},
            {" مليون", " ملايين"},
            {" مليار", " مليارات"},
            {" بليار", " بليارات"}
        };

    public ArabicTranslator() {
        this.lessThanThreeConditions.put(OperationType.INTEGER, this::resolveCurrencyForUnit);
        this.lessThanThreeConditions.put(OperationType.FRACTION, this::resolveCurrencyForSubUnit);
    }

    public String translate(Long number, Map<String, String> currency, OperationType type) {
        return number < 3L && number > 0L
            ? (String) ((BiFunction) this.lessThanThreeConditions.get(type)).apply(number, currency)
            : this.translateNumbersAboveThree(number, type, currency);
    }

    private String resolveCurrencyForUnit(Long number, Map<String, String> currency) {
        return this.getCurrency(
            number, currency.get("unit"), currency.get("displayName"));
    }

    private String resolveCurrencyForSubUnit(Long number, Map<String, String> currency) {
        return this.getCurrency(
            number, currency.get("subUnit"), currency.get("displayName"));
    }

    private String getCurrency(Long number, String unit, String displayName) {
        if (number != 1L && number != 0L) {
            return number == 2L ? unit + "ين" + " " + displayName : "";
        } else {
            return unit + " " + displayName;
        }
    }

    private String translateNumbersAboveThree(
        Long number, OperationType operationType, Map<String, String> currency) {
        for (Long i = number; i > 0L; i = i / 1000L) {
            numbers.get().push(i % 1000L);
        }

        StringBuilder result = new StringBuilder();

        while (!numbers.get().isEmpty()) {
            result.append(this.translateNumberPart());
        }

        return this.appendCurrency(
            operationType,
            currency.get("unit"),
            currency.get("subUnit"),
            currency.get("displayName"),
            result.toString());
    }

    private String translateNumberPart() {
        Long pop = (Long) ((Deque) numbers.get()).pop();
        int size = numbers.get().size();
        String postfix = this.families[size][0];
        String pluralPostfix = this.families[size][1];
        return this.translate(pop.intValue(), postfix, pluralPostfix, false)
            + (size != 0 && (Long) ((Deque) numbers.get()).peek() != 0L ? " و" : "");
    }

    private String appendCurrency(
        OperationType operationType, String unit, String subUnit, String displayName, String result) {
        return operationType == OperationType.INTEGER
            ? result.trim() + " " + unit + " " + displayName
            : result.trim() + " " + subUnit;
    }

    private String translate(int number, String postfix, String pluralPostfix, boolean isHundred) {
        if (number == 0) {
            return "";
        } else if (number == 1) {
            return isHundred ? dictionary.get(number) + postfix : postfix;
        } else if (number == 2) {
            return isHundred ? dictionary.get(number) + postfix : postfix + "ين";
        } else if (number <= 10) {
            return dictionary.get(number) + pluralPostfix;
        } else if (number < 100) {
            return dictionary.containsKey(number)
                ? dictionary.get(number) + postfix
                : dictionary.get(number % 10)
                + " و"
                + dictionary.get(number / 10 * 10)
                + postfix;
        } else {
            return this.getHundredPart(number, postfix, pluralPostfix);
        }
    }

    private String getHundredPart(int number, String postfix, String pluralPostfix) {
        String hundreds;
        if (number / 100 == 1) {
            hundreds = " مئه";
        } else if (number / 100 == 2) {
            hundreds = " مئتان";
        } else {
            hundreds = dictionary.get(number / 100) + " مئه";
        }

        return hundreds
            + (number % 100 != 0
            ? " و" + this.translate(number % 100, postfix, pluralPostfix, true)
            : postfix);
    }

    public String getDelimiter() {
        return " و ";
    }

    public String translateZero(Map<String, String> currency) {
        return "صفر " + this.resolveCurrencyForUnit(0L, currency);
    }
}
