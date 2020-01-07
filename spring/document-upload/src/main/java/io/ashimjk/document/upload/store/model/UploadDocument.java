package io.ashimjk.document.upload.store.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadDocument {

    private String originalFileName;
    private String contentType;
    private String folderName;

    @JsonSerialize(using = ByteArraySerializer.class)
    private byte[] content;

    @SneakyThrows
    public UploadDocument(MultipartFile file) {
        this.content = file.getBytes();
        this.originalFileName = file.getOriginalFilename();
        this.contentType = file.getContentType();
    }

}
