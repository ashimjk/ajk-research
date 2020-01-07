package io.ashimjk.document.upload.service.provider;

import io.ashimjk.document.upload.contracts.entity.Status;
import io.ashimjk.document.upload.contracts.entity.UploadedDocumentEntity;
import io.ashimjk.document.upload.contracts.repository.DocumentRepository;
import io.ashimjk.document.upload.service.ContentService;
import io.ashimjk.document.upload.service.provider.exception.UploadException;
import io.ashimjk.document.upload.store.DocumentStore;
import io.ashimjk.document.upload.store.model.ContentUploadResult;
import io.ashimjk.document.upload.store.model.DocumentList;
import io.ashimjk.document.upload.store.model.UploadDocument;
import io.ashimjk.document.upload.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DocumentContentService implements ContentService {

    private final DocumentStore store;
    private final DocumentRepository repository;

    @Override
    public UploadedDocumentEntity upload(String parentRef, String reference, DocumentList document) {
        List<UploadedDocumentEntity> documents = bulkUpload(parentRef, reference, document);
        return documents.isEmpty() ? null : documents.get(0);
    }

    @Override
    public List<UploadedDocumentEntity> bulkUpload(String parentRef, String reference, DocumentList list) {

        if (Objects.isNull(list.getUploadDocuments())) {
            return Collections.emptyList();
        }

        List<UploadedDocumentEntity> documents = list.getUploadDocuments().stream()
                .map((UploadDocument document) -> {
                    ContentUploadResult result = store.keep(document, Objects.isNull(parentRef) ? reference : parentRef);

                    return UploadedDocumentEntity
                            .builder()
                            .reference(IDGenerator.newId())
                            .category(list.getCategory())
                            .creationDate(LocalDateTime.now())
                            .fileName(document.getOriginalFileName())
                            .fileType(document.getContentType())
                            .moduleParentReference(parentRef)
                            .moduleReference(reference)
                            .objectId(result.getObjectId())
                            .status(Status.UPLOADED)
                            .build();
                })
                .collect(Collectors.toList());

        documents.forEach(repository::save);
        return documents;
    }

    @Override
    public List<UploadedDocumentEntity> bulkUpload(String reference, DocumentList list) {
        return bulkUpload(null, reference, list);
    }

    @Override
    public Set<UploadedDocumentEntity> findAll(String parentRef, String reference) {
        return repository.findByParentRefAndReference(parentRef, reference);
    }

    @Override
    public byte[] pullContent(String parentRef, String reference, String documentId) {

        UploadedDocumentEntity uploadedDocumentEntity = repository.findByParentRefAndReferenceAndDocumentId(parentRef, reference, documentId);
        return store.find(uploadedDocumentEntity.getObjectId()).getDoc();
    }

    @Override
    public void delete(String parentRef, String reference, String documentId, String... documentCategories) {

        UploadedDocumentEntity uploadedDocumentEntity = repository.findByParentRefAndReferenceAndDocumentId(parentRef, reference, documentId);
        if (documentCategories.length > 0) {

            boolean categoryBelongs = Arrays.asList(documentCategories).contains(uploadedDocumentEntity.getCategory());
            if (!categoryBelongs) {
                throw new UploadException("error", "not.allowed.to.upload.template.document");
            }
        }

        this.store.delete(uploadedDocumentEntity.getObjectId());
        uploadedDocumentEntity.setStatus(Status.DELETED);
        repository.update(uploadedDocumentEntity);
    }

}
