package io.ashimjk.document;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Objects;

@Primary
@Configuration
@ConfigurationProperties(prefix = "upload")
public class DocumentUploadConfig {

    public static final String ALFRESCO = "alfresco";
    public static final String IN_MEMORY = "in-memory";

    private String type;

    private String serviceUrl;

    private String username;

    private String password;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentUploadConfig that = (DocumentUploadConfig) o;
        return Objects.equals(serviceUrl, that.serviceUrl)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceUrl, username, password);
    }

}
