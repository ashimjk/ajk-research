package io.ashimjk.document.upload;

import io.ashimjk.document.upload.contracts.entity.UploadedDocumentEntity;
import io.ashimjk.document.upload.contracts.repository.DocumentRepository;
import io.ashimjk.document.upload.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class H2DocumentRepository implements DocumentRepository {

    private final DocumentJpaRepo repo;

    @Override
    public Set<UploadedDocumentEntity> findByParentRef(String parentRef) {
        return repo.findAllByModuleParentReference(parentRef);
    }

    @Override
    public Set<UploadedDocumentEntity> findByParentRefAndReference(String parentRef, String reference) {
        return repo.findAllByModuleParentReferenceAndModuleReference(parentRef, reference);
    }

    @Override
    public UploadedDocumentEntity update(UploadedDocumentEntity document) {
        return repo.save(document);
    }

    @Override
    public UploadedDocumentEntity save(UploadedDocumentEntity document) {
        document.setReference(IDGenerator.newId());
        return repo.save(document);
    }

    interface DocumentJpaRepo extends CrudRepository<UploadedDocumentEntity, String> {

        Set<UploadedDocumentEntity> findAllByModuleParentReference(String parentRef);

        Set<UploadedDocumentEntity> findAllByModuleParentReferenceAndModuleReference(String parentRef, String reference);

    }

}
