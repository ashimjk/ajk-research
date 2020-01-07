package io.ashimjk.document;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DocumentUploadConfig.class)
@RequiredArgsConstructor
public class UploadAutoConfiguration {

    private final DocumentUploadConfig upload;

}
