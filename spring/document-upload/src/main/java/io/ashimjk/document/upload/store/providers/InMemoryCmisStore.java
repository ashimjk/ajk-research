package io.ashimjk.document.upload.store.providers;

import io.ashimjk.document.DocumentUploadConfig;
import io.ashimjk.document.upload.store.DocumentStore;
import io.ashimjk.document.upload.store.model.ContentSharedResult;
import io.ashimjk.document.upload.store.model.ContentUploadResult;
import io.ashimjk.document.upload.store.model.UploadDocument;
import io.ashimjk.document.upload.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnProperty(name = "upload.type", havingValue = DocumentUploadConfig.IN_MEMORY)
@RequiredArgsConstructor
public class InMemoryCmisStore implements DocumentStore {

    private Map<String, byte[]> memories = new HashMap<>();

    @Override
    public ContentUploadResult keep(UploadDocument file, String reference) {
        String objectId = IDGenerator.newId();
        memories.put(objectId, file.getContent());
        return new ContentUploadResult(objectId);
    }

    @Override
    public ContentSharedResult find(String objectId) {
        return new ContentSharedResult(memories.get(objectId));
    }

    @Override
    public void delete(String objectId) {
        memories.remove(objectId);
    }

}
