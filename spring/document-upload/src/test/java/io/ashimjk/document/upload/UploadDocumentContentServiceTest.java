package io.ashimjk.document.upload;

import io.ashimjk.document.DocumentUploadConfig;
import io.ashimjk.document.upload.contracts.entity.Status;
import io.ashimjk.document.upload.contracts.entity.UploadedDocumentEntity;
import io.ashimjk.document.upload.contracts.exception.ResourceNotFoundException;
import io.ashimjk.document.upload.contracts.repository.DocumentRepository;
import io.ashimjk.document.upload.service.provider.DocumentContentService;
import io.ashimjk.document.upload.store.model.DocumentList;
import io.ashimjk.document.upload.store.model.UploadDocument;
import io.ashimjk.document.upload.store.providers.OpenCmisStore;
import io.ashimjk.document.upload.store.providers.OpenCmisStoreConfig;
import io.ashimjk.document.upload.util.IDGenerator;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DocumentApp.class, webEnvironment = NONE)
@DisplayName("Test UploadDocument Content Service")
class UploadDocumentContentServiceTest {

    private DocumentContentService service;
    @Autowired
    private DocumentRepository repository;

    @BeforeEach
    void setUp() {
        SessionFactory factory = mock(SessionFactory.class);
        Session session = mock(Session.class);
        Repository sessionRepository = mock(Repository.class);
        DocumentUploadConfig documentUploadConfig = mock(DocumentUploadConfig.class);

        when(sessionRepository.createSession()).thenReturn(session);
        when(factory.getRepositories(anyMap()))
                .thenReturn(Collections.singletonList(sessionRepository));
        when(documentUploadConfig.getServiceUrl()).thenReturn("http://localhost:3303/cmis");
        mockForOpenCmis(session);

        OpenCmisStoreConfig openCmisStoreConfig = new OpenCmisStoreConfig(documentUploadConfig);
        openCmisStoreConfig.setFactory(factory);

        OpenCmisStore store = new OpenCmisStore(openCmisStoreConfig);
        service = new DocumentContentService(store, repository);
    }

    @Test
    @DisplayName("Test UploadDocument Upload")
    void givenUploadedDocument_whenUpload_thenUploaded() {
        UploadedDocumentEntity uploadedDocumentEntity = uploadDoc(IDGenerator.newId(), IDGenerator.newId());

        assertNotNull(uploadedDocumentEntity.getReference());
        assertEquals("REQUEST", uploadedDocumentEntity.getCategory());
        assertEquals("PersonalDocument", uploadedDocumentEntity.getFileName());
        assertEquals("pdf", uploadedDocumentEntity.getFileType());
        assertNotNull(uploadedDocumentEntity.getModuleParentReference());
        assertNotNull(uploadedDocumentEntity.getModuleReference());
        Assertions.assertEquals(Status.UPLOADED, uploadedDocumentEntity.getStatus());
    }

    @Test
    @DisplayName("Test Bulk UploadDocument Upload")
    void givenUploadedDocumentList_whenBulkUpload_thenUploaded() {
        List<UploadedDocumentEntity> uploadedDocumentEntities =
                service.bulkUpload(
                        IDGenerator.newId(),
                        IDGenerator.newId(),
                        DocumentList.create(buildDocument(), "REQUEST"));
        assertTrue(uploadedDocumentEntities.size() > 0);
        for (UploadedDocumentEntity uploadedDocumentEntity : uploadedDocumentEntities) {
            assertNotNull(uploadedDocumentEntity.getReference());
            assertEquals("REQUEST", uploadedDocumentEntity.getCategory());
            assertEquals("PersonalDocument", uploadedDocumentEntity.getFileName());
            assertEquals("pdf", uploadedDocumentEntity.getFileType());
            assertNotNull(uploadedDocumentEntity.getModuleParentReference());
            assertNotNull(uploadedDocumentEntity.getModuleReference());
            Assertions.assertEquals(Status.UPLOADED, uploadedDocumentEntity.getStatus());
        }
    }

    @Test
    @DisplayName("Test Finding all Uploads")
    void givenModuleParentRefAndModuleReference_whenFindAll_thenReturnUploadedDocuments() {
        String parentRef = IDGenerator.newId();
        String reference = IDGenerator.newId();
        uploadDoc(parentRef, reference);
        uploadDoc(parentRef, reference);

        assertEquals(2, service.findAll(parentRef, reference).size());
    }

    @Test
    @DisplayName("Test Finding all Uploads for Invalid Input")
    void
    givenInvalidModuleParentRefAndModuleReference_whenFindAll_thenReturnEmptyUploadedDocuments() {
        String parentRef = IDGenerator.newId();
        String reference = IDGenerator.newId();
        assertEquals(0, service.findAll(parentRef, reference).size());
    }

    @Test
    @DisplayName("Validate Pull Content")
    void givenModuleParentRefAndModuleReference_whenPullContent_thenReturnBytes() {
        UploadedDocumentEntity doc = uploadDoc(IDGenerator.newId(), IDGenerator.newId());

        assertNotNull(
                service.pullContent(
                        doc.getModuleParentReference(), doc.getModuleReference(), doc.getReference()));
    }

    @Test
    @DisplayName("Illegal Pull Content Throws ResourceNotFoundException")
    void givenInvalidSearchParameter_whenPullContent_thenThrowsResourceNotFoundException() {
        String parentRef = IDGenerator.newId();
        String reference = IDGenerator.newId();
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> service.pullContent(parentRef, reference, reference),
                "Supposed to throw ResourceNotFoundException");
    }

    @Test
    @DisplayName("Test Delete with valid input")
    void givenExistedDocument_whenDelete_thenReturnDocumentHavingStatusDeleted() {
        UploadedDocumentEntity doc = uploadDoc(IDGenerator.newId(), IDGenerator.newId());
        service.delete(doc.getModuleParentReference(), doc.getModuleReference(), doc.getReference());

        UploadedDocumentEntity deletedDocument =
                repository.findByParentRefAndReferenceAndDocumentId(
                        doc.getModuleParentReference(), doc.getModuleReference(), doc.getReference());
        assertEquals(Status.DELETED, deletedDocument.getStatus());
    }

    @Test
    @DisplayName("Delete with invalid input throws ResourceNotFoundException")
    void givenInvalidRef_whenDelete_thenThrowException() {
        UploadedDocumentEntity doc = uploadDoc(IDGenerator.newId(), IDGenerator.newId());
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> service.delete("UnknownId", doc.getModuleReference(), doc.getReference()));
    }

    private UploadedDocumentEntity uploadDoc(String parentRef, String reference) {
        return service.upload(parentRef, reference, DocumentList.create(buildDocument(), "REQUEST"));
    }

    private void mockForOpenCmis(Session session) {
        Folder folder = Mockito.mock(Folder.class);
        ObjectFactory objectFactory = Mockito.mock(ObjectFactory.class);
        ContentStream contentStream = Mockito.mock(ContentStream.class);

        org.apache.chemistry.opencmis.client.api.Document document =
                Mockito.mock(org.apache.chemistry.opencmis.client.api.Document.class);
        when(document.getId()).thenAnswer(invocationOnMock -> IDGenerator.newId());

        when(contentStream.getStream())
                .thenReturn(getClass().getClassLoader().getResourceAsStream("uploadDocument.xml"));
        when(session.getObjectByPath(anyString())).thenReturn(folder);

        when(folder.getPath()).thenReturn("/demo");
        when(session.getRootFolder()).thenReturn(folder);
        when(session.getObjectFactory()).thenReturn(objectFactory);
        when(objectFactory.createContentStream(anyString(), anyInt(), anyString(), any()))
                .thenReturn(contentStream);
        when(folder.createDocument(Mockito.anyMap(), any(), any())).thenReturn(document);
        when(session.getContentStream(any())).thenReturn(contentStream);
    }

    private UploadDocument buildDocument() {
        return new UploadDocument() {
            @Override
            public byte[] getContent() {
                return new byte[0];
            }

            @Override
            public String getOriginalFileName() {
                return "PersonalDocument";
            }

            @Override
            public String getContentType() {
                return "pdf";
            }
        };
    }

}
