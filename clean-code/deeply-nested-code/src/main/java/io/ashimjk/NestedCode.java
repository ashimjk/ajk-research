package io.ashimjk;

import java.util.List;

class NestedCode {

    private final List<MappedField> persistenceFields;

    NestedCode(List<MappedField> persistenceFields) {this.persistenceFields = persistenceFields;}

    MappedField getMappedField(final String storedName) {

        for (final MappedField mf : persistenceFields) {
            if (mf.hasName(storedName)) {
                return mf;
            }
        }
        return null;
    }

    static class MappedField {

        private final List<String> names;

        MappedField(List<String> names) {this.names = names;}

        boolean hasName(String storedName) {
            return names.contains(storedName);
        }

    }

}
