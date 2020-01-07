package io.ashimjk.document.upload.store.providers;

import io.ashimjk.document.DocumentUploadConfig;
import io.ashimjk.document.upload.store.DocumentStore;
import io.ashimjk.document.upload.store.model.ContentSharedResult;
import io.ashimjk.document.upload.store.model.ContentUploadResult;
import io.ashimjk.document.upload.store.model.UploadDocument;
import io.ashimjk.document.upload.util.IDGenerator;
import lombok.SneakyThrows;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableConfigurationProperties(DocumentUploadConfig.class)
@ConditionalOnProperty(name = "upload.type", havingValue = DocumentUploadConfig.ALFRESCO)
public class OpenCmisStore implements DocumentStore {

    private OpenCmisStoreConfig config;

    public OpenCmisStore(OpenCmisStoreConfig config) {
        this.config = config;
    }

    @Override
    public ContentUploadResult keep(UploadDocument file, String reference) {

        String fileName = IDGenerator.newId() + "_" + file.getOriginalFileName();
        Session session = config.getSession();
        Folder target = findByParentReference(reference, session);
        byte[] bytes = file.getContent();

        Map<String, String> props = new HashMap<>();
        props.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
        props.put(PropertyIds.NAME, fileName);

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);

        ContentStream contentStream = session
                .getObjectFactory()
                .createContentStream(fileName, bytes.length, file.getContentType(), input);

        Document document = target.createDocument(props, contentStream, VersioningState.MAJOR);

        return new ContentUploadResult(document.getId());
    }

    @Override
    @SneakyThrows
    public ContentSharedResult find(String objectId) {
        ContentStream contentStream = config.getSession().getContentStream(new ObjectIdImpl(objectId));
        return new ContentSharedResult(IOUtils.toByteArray(contentStream.getStream()));
    }

    @Override
    public void delete(String objectId) {
        config.getSession().delete(new ObjectIdImpl(objectId), true);
    }

    private Folder findByParentReference(String parentRef, Session session) {
        String folderName = "docs_" + parentRef;
        try {
            CmisObject object = session.getObjectByPath(session.getRootFolder().getPath() + folderName);
            return (Folder) object;
        } catch (CmisObjectNotFoundException e) {
            return createFolder(session.getRootFolder(), folderName);
        }
    }

    private Folder createFolder(Folder target, String newFolderName) {
        Map<String, String> props = new HashMap<>();
        props.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
        props.put(PropertyIds.NAME, newFolderName);
        return target.createFolder(props);
    }

}
