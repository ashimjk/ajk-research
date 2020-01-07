package io.ashimjk.document.upload.store.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentList {

    private final List<UploadDocument> uploadDocuments;
    private final String category;

    public static DocumentList create(UploadDocument uploadDocument, String category) {
        return new DocumentList(Collections.singletonList(uploadDocument), category);
    }

    public static DocumentList update(UploadDocument uploadDocument, String category, String documentRef) {
        return new DocumentList(Collections.singletonList(uploadDocument), category);
    }

}
