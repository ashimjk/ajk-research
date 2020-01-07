package io.ashimjk.document.upload.store.providers;

import io.ashimjk.document.DocumentUploadConfig;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CmisVersion;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class OpenCmisStoreConfig {

    private final RetryPolicy<Object> retryPolicy =
            new RetryPolicy<>()
                    .handle(CmisObjectNotFoundException.class)
                    .withDelay(Duration.ofSeconds(5))
                    .withMaxRetries(5);

    private final DocumentUploadConfig upload;

    private SessionFactory factory;

    public OpenCmisStoreConfig(@Autowired DocumentUploadConfig upload) {
        this.upload = upload;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Session getSession() {
        Map<String, String> parameter = new HashMap<>();

        parameter.put(SessionParameter.USER, upload.getUsername());
        parameter.put(SessionParameter.PASSWORD, upload.getPassword());
        parameter.put(SessionParameter.ATOMPUB_URL, upload.getServiceUrl());
        parameter.put(SessionParameter.FORCE_CMIS_VERSION, CmisVersion.CMIS_1_1.value());
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

        return Failsafe.with(retryPolicy)
                .get(
                        () -> connect(factory == null ? SessionFactoryImpl.newInstance() : factory, parameter));
    }

    private Session connect(SessionFactory factory, Map<String, String> parameter) {
        return factory.getRepositories(parameter).get(0).createSession();
    }

}
