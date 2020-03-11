package io.ashimjk.objectmerge.util;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.ashimjk.objectmerge.util.DomainAsserts.isNullList;
import static io.ashimjk.objectmerge.util.DomainAsserts.nonNullList;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

public class MergeUtil {

    public static final class UpdateOrSetValue {

        /**
         * Update each value if available in {@code originalObjects}
         * or else add new value provided by {@code defaultValueSupplier}
         * <p>
         * example:
         * <pre>{@code
         * mergeValue(
         *     original.getContactPersons(),
         *     updated.getContactPersons(),
         *     ContactPerson::getReference,
         *     ContactPerson::setReference,
         *     ReferenceHandler::defaultReference
         * );
         * }</pre>
         *
         * @param originalObjects      original collection of objects from where values is retrieve
         * @param updatedObjects       updated collection of objects into which values are placed
         * @param getFunction          function for getting value
         * @param setFunction          function for setting value
         * @param defaultValueSupplier executed when no data available in {@code originalObjects}
         * @param <T>                  type of collection for {@code originalObjects} and {@code updatedObjects}
         * @param <D>                  type of return value from {@code getFunction}
         */
        public static <T, D> void mergeValue(List<T> originalObjects,
                                             List<T> updatedObjects,
                                             Function<T, D> getFunction,
                                             BiConsumer<T, D> setFunction,
                                             Supplier<D> defaultValueSupplier) {

            mergeObject(originalObjects, updatedObjects,
                    (original, updated) -> {
                        D value = getFunction.apply(original);
                        setFunction.accept(updated, value);
                    },
                    updated -> setFunction.accept(updated, defaultValueSupplier.get())
            );
        }

        /**
         * Update each values of list if available in {@code originalObjects}
         * or else add new values in the list provided by {@code defaultValueSupplier}
         *
         * <pre>{@code
         * mergeCollection(
         *     original.getAuthorizedSignatories(),
         *     updated.getAuthorizedSignatories(),
         *     AuthorizedSignature::getDelegatedPersons,
         *     DelegatedPerson::getReference,
         *     DelegatedPerson::setReference,
         *     ReferenceHandler::defaultReference
         * );
         * }</pre>
         *
         * @param originalObjects    collection of objects from where reference is retrieve
         * @param updatedObjects     collection of objects into which reference are placed
         * @param collectionFunction function to get values of collection
         * @param getRefFunction     function for getting value
         * @param setRefFunction     function for setting value
         * @param <T>                type of collection for {@code originalObjects} and {@code updatedObjects}
         * @param <R>                type of collection for getting and setting value
         */
        public static <T, R, D> void mergeCollection(List<T> originalObjects,
                                                     List<T> updatedObjects,
                                                     Function<T, List<R>> collectionFunction,
                                                     Function<R, D> getRefFunction,
                                                     BiConsumer<R, D> setRefFunction,
                                                     Supplier<D> defaultValueSupplier) {

            mergeObject(originalObjects, updatedObjects,
                    (original, updated) -> {
                        mergeValue(
                                collectionFunction.apply(original),
                                collectionFunction.apply(updated),
                                getRefFunction,
                                setRefFunction,
                                defaultValueSupplier);
                    },
                    updated -> ofNullable(collectionFunction.apply(updated))
                            .orElseGet(Collections::emptyList)
                            .forEach(obj -> setRefFunction.accept(obj, defaultValueSupplier.get()))
            );
        }

        /**
         * Update each object of list if available in {@code originalObjects}
         * or else add new object in the list provided by {@code defaultValueSupplier}
         *
         * <pre>{@code
         * mergeObject(
         *     original.getContactPersons(),
         *     updated.getContactPersons(),
         *     (cp, cp2) -> cp2.setReference(cp.getReference()),
         *     contactPerson -> contactPerson.setReference(UUID.randomUUID().toString())
         * );
         * }</pre>
         *
         * @param originalObjects collection of objects from where object is retrieve
         * @param updatedObjects  collection of objects into which object are placed
         * @param updateConsumer  function for updating value
         * @param setConsumer     function for setting value
         * @param <T>             type of collection for {@code originalObjects} and {@code updatedObjects}
         */
        private static <T> void mergeObject(List<T> originalObjects,
                                            List<T> updatedObjects,
                                            BiConsumer<T, T> updateConsumer,
                                            Consumer<T> setConsumer) {

            int maxLength = isNullList(updatedObjects) ? 0 : updatedObjects.size();

            for (int i = 0; i < maxLength; i++) {
                T original = getObject(i, originalObjects);
                T updated = getObject(i, updatedObjects);

                if (nonNull(original) && nonNull(updated)) {
                    updateConsumer.accept(original, updated);

                } else if (nonNull(updated)) {
                    setConsumer.accept(updated);
                }
            }
        }

        /**
         * Get object from collection if not empty
         *
         * @param index   position in the collection
         * @param objects collection of objects
         * @param <T>     type for collection object
         * @return object from list if empty return null
         */
        static <T> T getObject(int index, List<T> objects) {
            return isAvailable(index, objects) ? objects.get(index) : null;
        }

        private static <T> boolean isAvailable(int index, List<T> objects) {
            return nonNullList(objects) && index < objects.size();
        }

    }

    public static final class UpdateValue {

        /**
         * Update value if available in {@code originalObjects}
         *
         * <pre>{@code
         * merge(
         *     original.getTypeOfDealings(),
         *     updated.getTypeOfDealings(),
         *     TypeOfDealing::getId,
         *     TypeOfDealing::updateId
         * );
         * }</pre>
         *
         * @param originalObjects original collection of objects from where value is retrieve
         * @param updatedObjects  updated collection of objects into which value are placed
         * @param getFunction     function for getting value
         * @param setFunction     function for setting value
         * @param <T>             type of collection for {@code originalObjects} and {@code updatedObjects}
         * @param <D>             type of value for getting and setting value
         */
        static <T, D> void mergeValue(List<T> originalObjects,
                                      List<T> updatedObjects,
                                      Function<T, D> getFunction,
                                      BiConsumer<T, D> setFunction) {

            UpdateOrSetValue.mergeValue(
                    originalObjects, updatedObjects, getFunction, setFunction, () -> null);
        }

        /**
         * Update collection of value if available in {@code originalObjects}
         *
         * <pre>{@code
         * mergeCollection(
         *     original.getAuthorizedSignatories(),
         *     updated.getAuthorizedSignatories(),
         *     AuthorizedSignature::getDelegatedPersons,
         *     DelegatedPerson::getId,
         *     DelegatedPerson::setId
         * )
         * }</pre>
         *
         * @param originalObjects    original collection of objects from where value is retrieve
         * @param updatedObjects     updated collection of objects into which value are placed
         * @param collectionFunction function for get collection of value
         * @param getFunction        function for getting value
         * @param setFunction        function for setting value
         * @param <T>                type of collection for {@code originalObjects} and {@code updatedObjects}
         * @param <R>                type of collection value
         * @param <D>                type of collection for getting and setting value
         */
        static <T, R, D> void mergeCollection(List<T> originalObjects,
                                              List<T> updatedObjects,
                                              Function<T, List<R>> collectionFunction,
                                              Function<R, D> getFunction,
                                              BiConsumer<R, D> setFunction) {

            UpdateOrSetValue.mergeCollection(originalObjects,
                    updatedObjects, collectionFunction, getFunction, setFunction, () -> null);
        }

    }

}
