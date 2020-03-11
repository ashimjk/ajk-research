package io.ashimjk.objectmerge.util;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.ashimjk.objectmerge.util.DomainAsserts.isNullList;
import static io.ashimjk.objectmerge.util.MergeUtil.UpdateOrSetValue.getObject;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertList {

    public <T, R> void assertCollectionOfCollectionObject(List<T> originalObjects,
                                                          List<T> updatedObjects,
                                                          Function<T, List<R>> clObjFunction,
                                                          Function<R, Object> getFunction) {
        assertObjects(
                originalObjects,
                updatedObjects,
                (original, updated) -> {
                    if (nonNull(original) && nonNull(updated)) {
                        assertOriginalAndUpdated(clObjFunction.apply(original), clObjFunction.apply(updated), getFunction);

                    } else if (nonNull(updated)) {
                        assertOriginalAndUpdated(emptyList(), clObjFunction.apply(updated), getFunction);
                    }
                },
                updated -> assertOriginalAndUpdated(emptyList(), clObjFunction.apply(updated), getFunction)
        );
    }

    public <T> void assertOriginalAndUpdated(List<T> originalObjects, List<T> updatedObjects, Function<T, Object> function) {
        assertObjects(
                originalObjects,
                updatedObjects,
                (original, updated) -> assertThat(function.apply(updated)).isEqualTo(function.apply(original)),
                updated -> assertThat(function.apply(updated)).isNotNull()
        );
    }

    <T, R> void assertCollectionWithDefaultValueNull(List<T> originalObjects,
                                                     List<T> updatedObjects,
                                                     Function<T, List<R>> clObjFunction,
                                                     Function<R, Object> getFunction) {
        assertObjects(
                originalObjects,
                updatedObjects,
                (original, updated) -> {
                    if (nonNull(original) && nonNull(updated)) {
                        assertWithDefaultValueNull(clObjFunction.apply(original), clObjFunction.apply(updated), getFunction);

                    } else if (nonNull(updated)) {
                        assertWithDefaultValueNull(emptyList(), clObjFunction.apply(updated), getFunction);
                    }
                },
                updated -> assertWithDefaultValueNull(emptyList(), clObjFunction.apply(updated), getFunction)
        );
    }

    <T> void assertWithDefaultValueNull(List<T> originalObjects, List<T> updatedObjects, Function<T, Object> function) {
        assertObjects(
                originalObjects,
                updatedObjects,
                (original, updated) -> assertThat(function.apply(updated)).isEqualTo(function.apply(original)),
                updated -> assertThat(function.apply(updated)).isNull()
        );
    }

    private <T> void assertObjects(List<T> originalObjects,
                                   List<T> updatedObjects,
                                   BiConsumer<T, T> valueAssertBiConsumer,
                                   Consumer<T> defaultAssertConsumer) {
        int maxLength = isNullList(updatedObjects) ? 0 : updatedObjects.size();

        for (int i = 0; i < maxLength; i++) {
            T original = getObject(i, originalObjects);
            T updated = getObject(i, updatedObjects);

            if (nonNull(original) && nonNull(updated)) {
                valueAssertBiConsumer.accept(original, updated);

            } else if (nonNull(updated)) {
                defaultAssertConsumer.accept(updated);
            }
        }
    }

}
