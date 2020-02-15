package io.ashimjk.java.practice.collection.map.visualiser;

import java.lang.reflect.Field;

class Type {

    private final Class<?> cls;

    Type(final String name) {
        try {
            this.cls = Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    Type(final Class<?> cls) {
        this.cls = cls;
    }

    Field getField(final String fieldName) {
        try {
            final Field field = this.cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object get(final Field field, final Object object) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}
