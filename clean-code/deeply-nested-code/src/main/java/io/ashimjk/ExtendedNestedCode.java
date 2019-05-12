package io.ashimjk;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ExtendedNestedCode {

    private final List<MappedField> persistenceFields;

    void testCode(StoredDto storedDto) {
        List<String> personal = Arrays.asList("firstName", "lastName");
        List<String> addresses = Arrays.asList("city", "country");

        OriginalNestedCode.MappedField personalMapped = new OriginalNestedCode.MappedField(personal);
        OriginalNestedCode.MappedField addressMapped = new OriginalNestedCode.MappedField(addresses);

        getMappedField(m -> m.matchingNames(storedDto.getStoredName()));
        getMappedField(m -> m.matchingAddress(storedDto.getStoredAddress()));
        getMappedField(m -> m.matchingContact(storedDto.getStoredContact()));
    }

    ExtendedNestedCode(List<MappedField> persistenceFields) {this.persistenceFields = persistenceFields;}

    Optional<MappedField> getMappedField(Predicate<MappedField> isOk) {

        return persistenceFields
                .stream()
                .filter(isOk)
                .findFirst();

    }

    static class MappedField {

        private final List<String> names;
        private final List<String> addresses;
        private final List<String> contacts;

        MappedField(List<String> names, List<String> addresses, List<String> contacts) {
            this.names = names;
            this.addresses = addresses;
            this.contacts = contacts;
        }

        boolean matchingNames(String storedName) {
            return matchingStoredParamInParams(names, s -> s.equals(storedName));
        }

        boolean matchingAddress(String storedAddress) {
            return matchingStoredParamInParams(addresses, a -> a.equals(storedAddress));
        }

        boolean matchingContact(String storedContact) {
            return matchingStoredParamInParams(contacts, c -> c.equals(storedContact));
        }

        boolean matchingStoredParamInParams(List<String> params, Predicate<String> predicate) {
            for (final String n : params) {
                if (predicate.test(n)) {
                    return true;
                }
            }
            return false;
        }

    }

}
