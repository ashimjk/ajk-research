package io.ashimjk.objectmerge.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

@UtilityClass
public class ReferenceInitializer {

    public static <T, R> void setCollectionOfReferences(List<T> objects,
                                                        Function<T, List<R>> function,
                                                        BiConsumer<R, String> biConsumer) {
        objects.forEach(obj -> setReferences(function.apply(obj), biConsumer));
    }

    public static <T> void setReferences(List<T> objects, BiConsumer<T, String> biConsumer) {
        if (Objects.nonNull(objects)) {
            objects.forEach(obj -> setReference(obj, biConsumer));
        }
    }

    static <T> void setReference(T obj, BiConsumer<T, String> biConsumer) {
        biConsumer.accept(obj, uniqueId());
    }

    private static String uniqueId() {
        return UUID.randomUUID().toString();
    }

}
