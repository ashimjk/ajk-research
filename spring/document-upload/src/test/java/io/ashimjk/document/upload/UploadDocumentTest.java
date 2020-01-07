package io.ashimjk.document.upload;

import io.ashimjk.document.upload.store.model.UploadDocument;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadDocumentTest {

    @Test
    @SneakyThrows
    @MockitoSettings(strictness = Strictness.LENIENT)
    void givenMultipart_whenBuildRequest_thenBuildRequest() {
        MultipartFile mock = Mockito.mock(MultipartFile.class);
        when(mock.getBytes()).thenReturn(new byte[10]);
        when(mock.getOriginalFilename()).thenReturn("Demo.pdf");
        UploadDocument request = new UploadDocument(mock);

        assertEquals("Demo.pdf", request.getOriginalFileName());
        assertNotNull(request.getContent());
    }

}
