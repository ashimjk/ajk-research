package io.ashimjk.java.practice.collection.map;

public class BadHash {

    private final String value;
    private final int hash;

    public BadHash(String value, int hash) {
        this.value = value;
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        BadHash badHash = (BadHash) o;
        return this.value.equals(badHash.value);
    }

    @Override
    public int hashCode() {
        return this.hash;
    }

    @Override
    public String toString() {
        return "BadHash{" +
                "value='" + this.value + '\'' +
                ", hash=" + this.hash +
                '}';
    }

}
