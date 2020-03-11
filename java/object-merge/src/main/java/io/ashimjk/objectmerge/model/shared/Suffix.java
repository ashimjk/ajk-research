package io.ashimjk.objectmerge.model.shared;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum Suffix {

    AR("السيد", "السيده", "الانسه"),
    EN("Mr.", "Mrs.", "Ms.", "Miss");

    private List<String> suffixes;

    Suffix(String... suffixes) {
        this.suffixes = Arrays.asList(suffixes);
    }

    public static List<String> getSuffixes(String language) {
        if (language == null) {
            return getAllSuffixes();
        } else {
            return getSuffixesByLanguage(language);
        }

    }

    private static List<String> getAllSuffixes() {
        return Arrays.stream(values())
                .map(suffix -> suffix.suffixes)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<String> getSuffixesByLanguage(String language) {
        return Suffix.valueOf(language.toUpperCase())
                .suffixes;
    }

}
