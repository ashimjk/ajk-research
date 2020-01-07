package io.ashimjk.document.upload.store;

import io.ashimjk.document.upload.store.model.ContentSharedResult;
import io.ashimjk.document.upload.store.model.ContentUploadResult;
import io.ashimjk.document.upload.store.model.UploadDocument;

public interface DocumentStore {

    ContentUploadResult keep(UploadDocument file, String reference);

    ContentSharedResult find(String objectId);

    void delete(String objectId);

}
