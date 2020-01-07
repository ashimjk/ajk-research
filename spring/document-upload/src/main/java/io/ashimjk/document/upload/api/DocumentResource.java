package io.ashimjk.document.upload.api;

import io.ashimjk.document.upload.contracts.entity.UploadedDocumentEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentResource {

    private String parentModuleRef;
    private String moduleRef;
    private String fileId;
    private String fileName;
    private String fileType;
    private LocalDateTime creationDate;
    private String category;

    public static DocumentResource create(UploadedDocumentEntity document) {
        DocumentResource resource = new DocumentResource();
        resource.setParentModuleRef(document.getModuleParentReference());
        resource.setModuleRef(document.getModuleReference());
        resource.setFileId(document.getReference());
        resource.setCreationDate(document.getCreationDate());
        resource.setCategory(document.getCategory());
        resource.setFileName(document.getFileName());
        resource.setFileType(document.getFileType());
        return resource;
    }

}
