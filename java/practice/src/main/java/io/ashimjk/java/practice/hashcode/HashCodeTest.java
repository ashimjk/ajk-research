package io.ashimjk.java.practice.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HashCodeTest {

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        strs.add("ashim");
        strs.add("ashish");
        strs.add("pabitra");

        System.out.println(strs.hashCode());
        System.out.println(Arrays.hashCode(strs.toArray()));

        float f = 12.3f;
        System.out.println(Float.floatToIntBits(f));
    }

    static class HashCodeObject {

        private int id;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            HashCodeObject that = (HashCodeObject) o;
            return this.id == that.id &&
                    this.name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.id, this.name);
        }

    }

}
