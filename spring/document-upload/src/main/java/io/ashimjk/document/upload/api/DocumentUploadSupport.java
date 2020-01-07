package io.ashimjk.document.upload.api;

import io.ashimjk.document.upload.contracts.entity.Status;
import io.ashimjk.document.upload.contracts.entity.UploadedDocumentEntity;
import io.ashimjk.document.upload.service.ContentService;
import io.ashimjk.document.upload.store.model.DocumentList;
import io.ashimjk.document.upload.store.model.UploadDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@Component
@RequiredArgsConstructor
public class DocumentUploadSupport {

    private final ContentService service;

    public DocumentResource modify(String reference, String documentId, MultipartFile file, String category) {
        service.delete(null, reference, documentId, category);
        return upload(null, reference, file, category);
    }

    public DocumentResource modify(String parentRef, String reference, String documentId, MultipartFile file, String category) {
        service.delete(parentRef, reference, documentId, category);
        return upload(parentRef, reference, file, category);
    }

    public ResponseEntity<byte[]> findImage(String reference, String documentId) {
        return createByteResponse(reference, service.pullContent(null, reference, documentId));
    }

    public ResponseEntity<byte[]> findImage(String parentRef, String reference, String documentId) {
        return createByteResponse(reference, service.pullContent(parentRef, reference, documentId));
    }

    public DocumentResource upload(String reference, MultipartFile file, String documentCategory) {
        DocumentList documentList = DocumentList.create(new UploadDocument(file), documentCategory);
        UploadedDocumentEntity document = service.upload(null, reference, documentList);
        return DocumentResource.create(document);
    }

    public DocumentResource upload(String parentRef, String reference, MultipartFile file, String documentCategory) {
        DocumentList documentList = DocumentList.create(new UploadDocument(file), documentCategory);
        UploadedDocumentEntity document = service.upload(parentRef, reference, documentList);
        return DocumentResource.create(document);
    }

    public List<DocumentResource> loadAllDocuments(String reference) {
        return service.findAll(null, reference)
                .stream()
                .map(DocumentResource::create)
                .collect(toList());
    }

    public List<DocumentResource> loadAllDocuments(String parentRef, String reference) {
        return this.service.findAll(parentRef, reference).stream()
                .filter((e) -> e.getStatus() == Status.UPLOADED)
                .map(DocumentResource::create)
                .collect(Collectors.toList());
    }

    public ResponseEntity<byte[]> createByteResponse(String reference, byte[] bytes) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Content-Type", APPLICATION_PDF_VALUE);
        headers.set("Content-Disposition", "attachment; filename=" + reference + ".pdf");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
