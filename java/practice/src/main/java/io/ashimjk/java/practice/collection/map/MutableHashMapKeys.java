package io.ashimjk.java.practice.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MutableHashMapKeys {

    public static void main(String[] args) {
        final Map<MutableString, String> brokenMap = new HashMap<>();

        final String value = "abc";

        final MutableString key = new MutableString(value);
        brokenMap.put(key, value);

        System.out.println(brokenMap.get(key));
        System.out.println(brokenMap);

        key.set("def");

        System.out.println(brokenMap.get(key));
        System.out.println(brokenMap);

        key.set("abc");

        System.out.println(brokenMap.get(key));
        System.out.println(brokenMap);
    }

    private static class MutableString {

        private String value;

        public MutableString(String value) {
            this.set(value);
        }

        public void set(final String newValue) {
            Objects.requireNonNull(newValue);
            this.value = newValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            MutableString that = (MutableString) o;
            return this.value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "MutableString{" +
                    "value='" + value + '\'' +
                    '}';
        }

    }

}
