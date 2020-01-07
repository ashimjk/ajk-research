package io.ashimjk.document.upload.contracts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadedDocumentEntity implements Serializable {

    private static final long serialVersionUID = -2604376724406815079L;

    private String reference;
    private String fileName;
    private String fileType;
    private String objectId;
    private String category;
    private String moduleReference;
    private String moduleParentReference;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadedDocumentEntity that = (UploadedDocumentEntity) o;
        return Objects.equals(objectId, that.objectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId);
    }

}
