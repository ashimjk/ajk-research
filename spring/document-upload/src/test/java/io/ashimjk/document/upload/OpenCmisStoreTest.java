package io.ashimjk.document.upload;

import io.ashimjk.document.DocumentUploadConfig;
import io.ashimjk.document.upload.store.model.ContentSharedResult;
import io.ashimjk.document.upload.store.model.ContentUploadResult;
import io.ashimjk.document.upload.store.model.UploadDocument;
import io.ashimjk.document.upload.store.providers.OpenCmisStore;
import io.ashimjk.document.upload.store.providers.OpenCmisStoreConfig;
import lombok.SneakyThrows;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.FolderImpl;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.client.runtime.repository.ObjectFactoryImpl;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing CMIS Services")
class OpenCmisStoreTest {

    private static final String OPEN_CMIS = "OPEN CMIS";
    private static final String REF = "ref";

    @InjectMocks
    private OpenCmisStoreConfig openCmisStoreConfig;
    private OpenCmisStore store;
    @Mock
    private SessionFactory factory;
    @Mock
    private Session openCmis;
    @Mock
    private Folder folder;
    @Mock
    private DocumentUploadConfig documentUploadConfig;
    @Mock
    private FolderImpl target;
    @Mock
    private Repository repository;

    @BeforeEach
    void setUp() {
        when(repository.createSession()).thenReturn(openCmis);
        when(factory.getRepositories(anyMap())).thenReturn(Collections.singletonList(repository));
        when(documentUploadConfig.getServiceUrl()).thenReturn("http://localhost:3303/cmis");

        openCmisStoreConfig.setFactory(factory);

        store = new OpenCmisStore(openCmisStoreConfig);
    }

    @Test
    @DisplayName("Test Storing Doc in Memory")
    @SneakyThrows
    void givenUploadRequest_whenKeep_shouldReturnObjectId() {

        when(openCmis.getRootFolder()).thenReturn(folder);
        when(openCmis.getObjectByPath(anyString())).thenReturn(target);
        when(openCmis.getObjectFactory()).thenReturn(mock(ObjectFactoryImpl.class));

        MultipartFile file = Mockito.mock(MultipartFile.class);

        when(file.getBytes()).thenReturn(new byte[10]);
        when(file.getOriginalFilename()).thenReturn("Demo.pdf");

        Document document = mock(Document.class);
        when(document.getId()).thenReturn(REF);

        when(target.createDocument(anyMap(), any(), any())).thenReturn(document);
        UploadDocument request = new UploadDocument(file);

        ContentUploadResult result = store.keep(request, REF);

        assertNotNull(result.getObjectId());
    }

    @Test
    @DisplayName("Test finding doc in Storage")
    void canFind() {
        ContentStream mock = mock(ContentStream.class);

        byte[] bytes = OPEN_CMIS.getBytes();

        when(mock.getStream()).thenReturn(new ByteArrayInputStream(bytes));
        when(openCmis.getContentStream(new ObjectIdImpl(REF))).thenReturn(mock);

        ContentSharedResult result = store.find(REF);

        assertNotNull(result.getDoc());
    }

}
