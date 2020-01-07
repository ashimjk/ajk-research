package io.ashimjk.document.upload.contracts.repository;

import io.ashimjk.document.upload.contracts.entity.UploadedDocumentEntity;
import io.ashimjk.document.upload.contracts.exception.ResourceNotFoundException;

import java.util.Set;

public interface DocumentRepository {

    Set<UploadedDocumentEntity> findByParentRef(String parentRef);

    Set<UploadedDocumentEntity> findByParentRefAndReference(String parentRef, String reference);

    UploadedDocumentEntity update(UploadedDocumentEntity document);

    default UploadedDocumentEntity findByParentRefAndReferenceAndDocumentId(String parentRef, String reference, String documentId) {

        return findByParentRefAndReference(parentRef, reference).stream()
                .filter(e -> e.getReference().equals(documentId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("cannot find document with id " + documentId));
    }

    UploadedDocumentEntity save(UploadedDocumentEntity document);

}
