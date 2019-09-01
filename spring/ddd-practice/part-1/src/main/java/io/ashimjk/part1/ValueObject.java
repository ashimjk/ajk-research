package io.ashimjk.part1;

abstract class ValueObject<T> {

    @Override
    public int hashCode() {
        return hashCodeCore();
    }

    protected abstract int hashCodeCore();

    @Override
    public boolean equals(Object obj) {
        return equalsCore(obj);
    }

    protected abstract boolean equalsCore(Object obj);

}
