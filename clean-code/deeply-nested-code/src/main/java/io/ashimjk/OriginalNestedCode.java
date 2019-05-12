package io.ashimjk;

import java.util.List;

class OriginalNestedCode {

    private final List<MappedField> persistenceFields;

    OriginalNestedCode(List<MappedField> persistenceFields) {this.persistenceFields = persistenceFields;}

    MappedField getMappedField(final String storedName) {

        for (final MappedField mf : persistenceFields) {
            for (final String n : mf.getLoadNames()) {
                if (storedName.equals(n)) {
                    return mf;
                }
            }
        }
        return null;
    }

    static class MappedField {

        private final List<String> names;

        MappedField(List<String> names) {this.names = names;}

        List<String> getLoadNames() {
            return names;
        }

    }

}
