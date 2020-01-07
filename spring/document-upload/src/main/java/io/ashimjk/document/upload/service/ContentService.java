package io.ashimjk.document.upload.service;

import io.ashimjk.document.upload.contracts.entity.UploadedDocumentEntity;
import io.ashimjk.document.upload.store.model.DocumentList;

import java.util.List;
import java.util.Set;

public interface ContentService {

    UploadedDocumentEntity upload(String parentRef, String reference, DocumentList list);

    List<UploadedDocumentEntity> bulkUpload(String parentRef, String reference, DocumentList list);

    List<UploadedDocumentEntity> bulkUpload(String reference, DocumentList list);

    Set<UploadedDocumentEntity> findAll(String parentRef, String reference);

    byte[] pullContent(String parentRef, String reference, String documentId);

    void delete(String parentRef, String reference, String documentId, String... documentCategories);

}
